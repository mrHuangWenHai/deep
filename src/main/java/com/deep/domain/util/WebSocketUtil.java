package com.deep.domain.util;

import com.deep.domain.service.MyWebSocket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WebSocketUtil {
    //记录用户id与其websocket会话的映射关系
    private static Map<Long, MyWebSocket> map = new ConcurrentHashMap<Long, MyWebSocket>();

    public static Map<Long, MyWebSocket> getMap() {
        return map;
    }

    public static MyWebSocket put(long user_id, MyWebSocket session) {
        return map.put(getKey(user_id), session);
    }

    public static MyWebSocket get(long user_id) {
        return map.get(getKey(user_id));
    }

    public static int getSize() {
        return map.size();
    }

    public static void remove(long user_id) {
        map.remove(getKey(user_id));
    }

    private static long getKey(long user_id) {
        return user_id;
    }

}