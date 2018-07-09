package com.deep.domain.service;

import com.alibaba.fastjson.JSON;
import com.deep.domain.model.ResponseBean;
import com.deep.domain.util.WebSocketUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

@Service
public class DynamicTask {

    protected static Logger logger = LoggerFactory.getLogger(DynamicTask.class);

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    private ScheduledFuture<?> future;
    private static Map<String, ScheduledFuture<?>> con_fuMap = new ConcurrentHashMap<>();

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    static Map<String, ScheduledFuture<?>> getTaskMap(){
        return con_fuMap;
    }

    void startGroupChat(String conversation_id) {
        reset(conversation_id);
        logger.info(conversation_id + " fresh");
        Runnable runnable = new GroupRunnable(conversation_id);
        Trigger trigger = new PeriodicTrigger(0);
        ((PeriodicTrigger) trigger).setInitialDelay(43200000);
        future = threadPoolTaskScheduler.schedule(runnable, trigger);
        con_fuMap.put(conversation_id, future);
    }

    void startPrivateChat(Long target_id, String conversation_id) {
        reset(conversation_id);
        logger.info(conversation_id + " fresh");
        Runnable runnable = new PrivateRunnable(target_id, conversation_id);
        Trigger trigger = new PeriodicTrigger(0);
        ((PeriodicTrigger) trigger).setInitialDelay(600000);
        future = threadPoolTaskScheduler.schedule(runnable, trigger);
        con_fuMap.put(conversation_id, future);
    }

    private void reset(String conversation_id) {
        if (con_fuMap.get(conversation_id) != null) {
            con_fuMap.get(conversation_id).cancel(true);
        }
    }

    private class GroupRunnable implements Runnable {
        private String conversation_id;

        GroupRunnable(String conversation_id) {
            this.conversation_id = conversation_id;
        }

        @Override
        public void run() {
            Map<String, List<Long>> chatMap = MyWebSocket.getChatMap();
            Map<Long, Set<String>> accountMap = MyWebSocket.getAccountMap();
            Map<Long, MyWebSocket> socketMap = WebSocketUtil.getMap();
            List<Long> members = new ArrayList<>(chatMap.get(conversation_id));
            for (Long member : members) {
                MyWebSocket socket = socketMap.get(member);
                if (socket != null)
                    socket.getSession().getAsyncRemote().sendText(JSON.toJSONString(new ResponseBean(null, "disband", conversation_id, null)));
                accountMap.get(member).remove(conversation_id);
                if (accountMap.get(member).size() == 0)
                    accountMap.remove(member);
            }
            chatMap.remove(conversation_id);
            con_fuMap.get(this.conversation_id).cancel(true);
            if (con_fuMap.get(this.conversation_id).isCancelled()) {
                con_fuMap.remove(this.conversation_id);
                logger.info("stop group " + conversation_id + " finished");
            }
        }
    }

    private class PrivateRunnable implements Runnable {
        private Long target_id;
        private String conversation_id;

        PrivateRunnable(Long target_id, String conversation_id) {
            this.target_id = target_id;
            this.conversation_id = conversation_id;
        }

        @Override
        public void run() {
            Map<Long, String> personalMap = MyWebSocket.getPersonalMap();
            personalMap.remove(target_id);
            System.out.println("单聊结束");
            con_fuMap.get(conversation_id).cancel(true);
            if (con_fuMap.get(conversation_id).isCancelled()) {
                con_fuMap.remove(conversation_id);
                logger.info("stop private " + conversation_id + " finished");
            }
        }
    }

}