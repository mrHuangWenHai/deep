package com.deep.api.Utils;

import com.deep.domain.service.ServiceConfiguration;
import redis.clients.jedis.Jedis;

public class JedisUtil {
    public static int seconds = 3600;

    public static String getValue(String key) {
        Jedis jedis = RedisPool.getJedis();
        if (jedis == null) {
            return null;
        }
        try {
          if (!jedis.exists(key)) {
            return null;
          } else {
            return jedis.get(key);
          }
        } catch (Exception e ) {
       //   jedis.close();
        }
        return null;
    }

    public static void setValue(String key, String value) {
        Jedis jedis = RedisPool.getJedis();
        if (jedis != null) {
            try {
                jedis.setex(key, seconds, value);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void doExpire(String key) {
        System.out.println("doExpire ==================================== "+key+"         seconds    ===" +seconds);
        Jedis jedis = RedisPool.getJedis();
        if (jedis != null) {
            try {
                jedis.expire(key,seconds);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean doDelete(String key) {
        Jedis jedis = RedisPool.getJedis();
        if (jedis != null) {
            try {
                jedis.del(key);
                System.out.println("Yes, it is");
            } catch (Exception e) {
                System.out.println("Something must be wrong!");
                return false;
            }
        }
        return true;
    }
}
