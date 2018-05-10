package com.deep.api.Utils;

import com.deep.domain.service.ServiceConfiguration;
import redis.clients.jedis.Jedis;

public class JedisUtil {
    public static Jedis jedis = new Jedis(ServiceConfiguration.redisServer, ServiceConfiguration.port);

    public static int seconds = 3600;

    public static String getValue(String key) {

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
         jedis.setex(key, seconds, value);
      //  jedis.set(key,value);
    }

    public static void doExpire(String key) {
        System.out.println("doExpire ==================================== "+key+"         seconds    ===" +seconds);
        try {
          jedis.expire(key,seconds);
        } catch (Exception e) {
          System.out.println(e);
        }

    }

    public static boolean doDelete(String key) {
        try {
            jedis.del(key);
            System.out.println("Yes, it is");
        } catch (Exception e) {
            System.out.println("Something must be wrong!");
            return false;
        }
        return true;
    }
}
