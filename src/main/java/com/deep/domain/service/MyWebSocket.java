package com.deep.domain.service;

import com.alibaba.fastjson.JSON;
import com.deep.domain.model.MsgBean;
import com.deep.domain.model.ResponseBean;
import com.deep.domain.util.WebSocketUtil;
import org.apache.commons.beanutils.ConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by 10742 on 2018/2/7.
 */
@Component
@ServerEndpoint(value = "/websocket/{user_id}")
public class MyWebSocket {
    protected static Logger logger = LoggerFactory.getLogger(MyWebSocket.class);
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    private static ApplicationContext act = ApplicationContextRegister.getApplicationContext();
    private static TalkService talkService = act.getBean(TalkService.class);
    private static DynamicTask task = act.getBean(DynamicTask.class);
    //记录会话id与群聊成员id的映射关系
    private static Map<String, List<Long>> chatMap = new ConcurrentHashMap<>();
    //记录用户id与其所处所有群聊中的会话id的映射关系
    private static Map<Long, Set<String>> accountMap = new ConcurrentHashMap<>();
    //记录用户id与单聊会话id的映射关系
    private static Map<Long, String> personalMap = new ConcurrentHashMap<>();
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;

    public Session getSession() {
        return session;
    }

    public static Map<String, List<Long>> getChatMap() {
        return chatMap;
    }

    public static Map<Long, String> getPersonalMap() {
        return personalMap;
    }

    public static Map<Long, Set<String>> getAccountMap() {
        return accountMap;
    }

    /**
     * 连接建立成功调用的方法
     *
     * @param user_id 用户id
     */
    @OnOpen
    public void onOpen(@PathParam("user_id") Long user_id, Session session) {
        this.session = session;
        WebSocketUtil.put(user_id, this);
        logger.info(user_id + " 进入！当前在线人数为" + WebSocketUtil.getSize());
    }

    /**
     * 连接关闭调用的方法
     *
     * @param user_id 用户id
     */
    @OnClose
    public void onClose(@PathParam("user_id") Long user_id) {
        WebSocketUtil.remove(user_id);
        logger.info("Account " + user_id + " close!" + WebSocketUtil.getSize() + " online");
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        logger.info(message);
        MsgBean msgBean = JSON.parseObject(message, MsgBean.class);
        if (!msgBean.getTalk_id().equals("null") && !msgBean.getUser_id().equals("null"))
            sendMessage(msgBean.getMessage(), Boolean.valueOf(msgBean.getIsExpert()), Long.valueOf(msgBean.getUser_id()), msgBean.getName(), msgBean.getTo(), msgBean.getMode(), msgBean.getTalk_id());
        else
            this.getSession().getAsyncRemote().sendText(JSON.toJSONString(new ResponseBean("无专家在线", "error", null, null)));
    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error) {
        session.getAsyncRemote().sendText(JSON.toJSONString(new ResponseBean(error.getMessage(), "error", null, null)));
        logger.info(error.getMessage());
    }

    /**
     * @param message  客户端发送过来的消息
     * @param isExpert 角色id
     * @param user_id  用户id
     * @param name     用户名
     * @param tos      群聊用户id
     * @param mode     0:服务器转发信息 1：发起建立群聊的信号 2：群聊 3:退出群聊
     * @param talk_id  会话id
     */
    private void sendMessage(String message, Boolean isExpert, Long user_id, String name, Long[] tos, int mode, String talk_id) {
        switch (mode) {
            case 0: {
                //获取聊天对象的会话session
                MyWebSocket item = WebSocketUtil.get(Long.valueOf(talk_id));
                Date date = new Date();
                //给当前单聊生成一个聊天id,记录用户id与聊天id的映射关系,并绑定定时任务
                Long target_id = initPrivateState(user_id, Long.valueOf(talk_id), isExpert);
                //若对方不在线,记录聊天内容标记为未处理状态
                if (item == null) {
                    this.getSession().getAsyncRemote().sendText(JSON.toJSONString(new ResponseBean(message, "self", talk_id, name)));
                    record(personalMap.get(target_id), name, user_id, Long.valueOf(talk_id), message, date, false, false);
                } else {
                    if (isExpert && accountMap.containsKey(Long.valueOf(talk_id)))
                        this.getSession().getAsyncRemote().sendText(JSON.toJSONString(new ResponseBean("用户正在群聊中", "self", talk_id, name)));
                    else {
                        task.startPrivateChat(target_id, personalMap.get(target_id));
                        item.getSession().getAsyncRemote().sendText(JSON.toJSONString(new ResponseBean(message, null, String.valueOf(user_id), name)));
                        this.getSession().getAsyncRemote().sendText(JSON.toJSONString(new ResponseBean(message, "self", talk_id, name)));
                        record(personalMap.get(target_id), name, user_id, Long.valueOf(talk_id), message, date, false, true);
                    }
                }
                break;
            }
            case 1: {
                //给当前群聊生成一个聊天id,并记录群聊id与群成员,用户id与群聊id的映射关系,并绑定定时任务
                List<Long> members = new ArrayList<>(Arrays.asList(tos));
                //给对应id添加角色标志
                talk_id = initGroupState(user_id, talk_id, members);
                for (Long member : members) {
                    MyWebSocket account = WebSocketUtil.get(member);
                    addAccount(talk_id, member, null);
                    if (account != null)
                        account.getSession().getAsyncRemote().sendText(JSON.toJSONString(new ResponseBean(null, "fresh", talk_id, null)));
                }
                System.out.println(accountMap);
                System.out.println(chatMap);
                break;
            }
            case 2: {
                //根据群聊成员列表对消息进行转发,并刷新定时任务
                Date date = new Date();
                List<Long> list = chatMap.get(talk_id);
                for (Long aList : list) {
                    MyWebSocket account = WebSocketUtil.get(aList);
                    if (account != null) {
                        if (account != this)
                            account.getSession().getAsyncRemote().sendText(JSON.toJSONString(new ResponseBean(message, null, talk_id, name)));
                        else
                            account.getSession().getAsyncRemote().sendText(JSON.toJSONString(new ResponseBean(message, "self", talk_id, name)));
                    }
                }
                task.startGroupChat(talk_id);
                record(talk_id, name, user_id, 0L, message, date, false, true);
                break;
            }
            case 3:
                //获取当前群聊的成员聊表,移除该成员,更新映射表
                List<Long> members = new ArrayList<>(chatMap.get(talk_id));
                members.remove(user_id);
                //群聊列表只剩一名成员,聊天自动解散
                if (members.size() == 1) {
                    MyWebSocket account = WebSocketUtil.get(members.get(0));
                    if (account != null) {
                        account.getSession().getAsyncRemote().sendText(JSON.toJSONString(new ResponseBean(null, "disband", talk_id, null)));
                    }
                    chatMap.remove(talk_id);
                } else {
                    for (Long member : members) {
                        MyWebSocket account = WebSocketUtil.get(member);
                        if (account != null)
                            account.getSession().getAsyncRemote().sendText(JSON.toJSONString(new ResponseBean(null, "exit", talk_id, name)));
//            if (account != null && !isExpert)
//              account.getSession().getAsyncRemote().sendText(JSON.toJSONString(new ResponseBean(null, "exit", talk_id, name)));
//            else if (account != null && isExpert)
//              account.getSession().getAsyncRemote().sendText(JSON.toJSONString(new ResponseBean(null, "exit", talk_id, name)));
                    }
                    chatMap.put(talk_id, members);
                }
                accountMap.get(user_id).remove(talk_id);
                if (accountMap.get(user_id).size() == 0)
                    accountMap.remove(user_id);
                System.out.println(accountMap);
                System.out.println(chatMap);
                break;
        }
    }

//  private static boolean isExpert(Long role_id) {
//    return role_id == 16 || role_id == 12 || role_id == 8 || role_id == 4;
//  }

    private static void addAccount(String talk_id, Long user_id, Boolean isMainExpert) {
        if (!accountMap.containsKey(user_id)) {
            accountMap.put(user_id, new HashSet<>());
            talkService.member_record(talk_id, user_id, isMainExpert);
        }
        accountMap.get(user_id).add(talk_id);
    }

    /**
     * 聊天内容记录
     */
    public static void record(String talk_id, String talker_name, Long talker_id, Long receiver_id, String message, Date time, boolean isLink, boolean isHandle) {
        if (talkService.talkRecode(talk_id, talker_name, talker_id, receiver_id, message, time, isLink, isHandle) > 0)
            logger.info("(talk_id, talker_name, talker_id, receiver_id, content, start_time, isHandle) : (\"" + talk_id + "\",\"" + talker_name + "\"," + talker_id + "," + receiver_id + ",\"" + message + "\",\"" + time + "\"," + isLink + "," + isHandle + ")");
        else
            logger.info("insert error");
    }

    /**
     * 设置会话id
     */
    private static String getConversationID(Long user_id) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss-");
        Date date = new Date();
        return format.format(date) + user_id;
    }

    public static Long initPrivateState(Long user_id, Long talk_id, Boolean role_id) {
        Long target_id = role_id ? talk_id : user_id;
        if (personalMap.get(target_id) == null) {
            String conversation_id = getConversationID(target_id);
            talkService.member_record(conversation_id, talk_id, true);
            talkService.member_record(conversation_id, user_id, false);
            personalMap.put(user_id, conversation_id);
            task.startPrivateChat(user_id, conversation_id);
        }
        System.out.println(personalMap);
        return target_id;
    }

    public static String initGroupState(Long user_id, String talk_id, List<Long> members) {
        if (chatMap.get(talk_id) == null) {
            String conversation_id = getConversationID(user_id);
            addAccount(conversation_id, Long.valueOf(talk_id), false);
            addAccount(conversation_id, user_id, true);
            chatMap.put(conversation_id, members);
            task.startGroupChat(conversation_id);
            return conversation_id;
        } else
            task.startGroupChat(talk_id);
        return talk_id;
    }

//    private static synchronized int getOnlineCount() {
//        return onlineCount;
//    }
//
//    private static synchronized void addOnlineCount() {
//        MyWebSocket.onlineCount++;
//    }
//
//    private static synchronized void subOnlineCount() {
//        MyWebSocket.onlineCount--;
//    }
}
