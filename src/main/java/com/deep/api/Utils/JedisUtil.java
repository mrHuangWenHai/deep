package com.deep.api.Utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

public class JedisUtil {
    public static int seconds = 3600;
    public static Logger logger = LoggerFactory.getLogger(JedisUtil.class);

    public static String getValue(String key) {
        Jedis jedis = RedisPool.getJedis();
        if (jedis == null) {
            logger.info("jedis == null");
            return null;
        }
        try {
          if (!jedis.exists(key)) {
            return null;
          } else {
            return jedis.get(key);
          }
        } catch (Exception e ) {
            logger.info("获取异常 {}",e.getMessage());
       //   jedis.close();
        } finally {
            try {
                jedis.close();
            } catch (Exception e) {
                logger.info("关闭异常 {}",e.getMessage());
            }

        }
        return null;
    }

    public static void setValue(String key, String value) {
        Jedis jedis = RedisPool.getJedis();
        if (jedis != null) {
            try {
                jedis.setex(key, seconds, value);
            } catch (Exception e) {
                logger.info("设置异常 {}",e.getMessage());
            } finally {
                try {
                    jedis.close();
                } catch (Exception e) {
                    logger.info("关闭异常 {}",e.getMessage());
                }

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
                logger.info("时间设置异常 {}",e.getMessage());
            } finally {
                try {
                    jedis.close();
                } catch (Exception e) {
                    logger.info("关闭异常 {}",e.getMessage());
                }
            }
        }
    }

    public static boolean doDelete(String key) {
        Jedis jedis = RedisPool.getJedis();
        if (jedis != null) {
            try {
                jedis.del(key);
                logger.info("删除成功");

            } catch (Exception e) {
                logger.info("删除异常 {}",e.getMessage());
                return false;
            } finally {
                try {
                    jedis.close();
                } catch (Exception e) {
                    logger.info("关闭异常 {}",e.getMessage());
                }
            }
        }
        return true;
    }
}
