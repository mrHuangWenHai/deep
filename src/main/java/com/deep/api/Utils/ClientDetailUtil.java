package com.deep.api.Utils;

import com.alibaba.fastjson.JSON;
import com.deep.api.response.ClientDetailResponse;
import org.springframework.stereotype.Component;

@Component
public class ClientDetailUtil {
    private static String preString = "clientDetail";

    // 判断Redis是否读入客户关系信息
    public static boolean isLoad() {
        String value = JedisUtil.getValue(preString);
        return value != null;
    }

    // 讲客户关系一览表中的数据存入Redis
    public static void setClientToRedis(Long factory, Byte flag, ClientDetailResponse clientDetailResponse) {
        String key = preString + "-" + factory + "-" + flag;
        JedisUtil.setValue(key, JSON.toJSONString(clientDetailResponse));
        JedisUtil.doPersist(key);
    }

    // 查找一条数据
    public static String getClientToRedis(Long factory, Byte flag) {
        String key = preString + "-" + factory + "-" + flag;
        return JedisUtil.getValue(key);
    }

    // 删除客户关系一览表中的数据
    public static boolean deleteClientToRedis(Long factory, Byte flag) {
        String key = preString + "-" + factory + "-" + flag;
        return JedisUtil.doDelete(key);
    }

    // 重置客户关系一览表中的数据，首先删除，再重新设置
    public static void resetClientToRedis(Long factory, Byte flag, ClientDetailResponse clientDetailResponse) {
        String key = preString + "-" + factory + "-" + flag;
        JedisUtil.doDelete(key);
        JedisUtil.setValue(key, JSON.toJSONString(clientDetailResponse));
    }
}
