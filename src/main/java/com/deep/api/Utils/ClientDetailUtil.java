package com.deep.api.Utils;

import com.deep.api.response.ClientDetailResponse;
import org.springframework.stereotype.Component;

@Component
public class ClientDetailUtil {
    private String preString = "clientDetail";

    // 判断Redis是否读入客户关系信息
    public boolean isLoad() {
        String value = JedisUtil.getValue(preString);
        return value != null;
    }

    // 讲客户关系一览表中的数据存入Redis
    public void setClientToRedis(Long factory, Byte flag, ClientDetailResponse clientDetailResponse) {
        String key = preString + "-" + factory + "-" + flag;
        JedisUtil.setValue(key, clientDetailResponse.toString());
        JedisUtil.doPersist(key);
    }

    // 删除客户关系一览表中的数据
    public boolean deleteClientToRedis(Long factory, Byte flag) {
        String key = preString + "-" + factory + "-" + flag;
        return JedisUtil.doDelete(key);
    }

    // 重置客户关系一览表中的数据，首先删除，再重新设置
    public void resetClientToRedis(Long factory, Byte flag, ClientDetailResponse clientDetailResponse) {
        String key = preString + "-" + factory + "-" + flag;
        JedisUtil.doDelete(key);
        JedisUtil.setValue(key, clientDetailResponse.toString());
    }
}
