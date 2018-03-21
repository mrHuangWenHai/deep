package com.deep.domain.util;

import redis.clients.jedis.Jedis;

/**
 * create by zhongrui on 18-3-21.
 * Redis工具类
 * 用于操作与redis相关的数据行为
 */
public class JedisUtil {
    private String key;
    private String value;

    public JedisUtil() {
    }

    public JedisUtil(String key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * 用户存储专家/监督员的剩余工作
     * key:"factory_num+模块名+专家/监督员"
     * value:未审核条数
     *
     * 功能：在操作员添加了一条数据后,redis中对应数据+1
     * @param key
     */
    public void redisSaveProfessorSupervisorWorks(String key){
        Jedis jedis = new Jedis("localhost");
        String temValue = jedis.get(key);
        if(temValue == null){
            jedis.set(key,"1");
            //System.out.println("插入后的redis(first):"+key+" "+jedis.get(key));
        }else {
            Integer v = Integer.parseInt(temValue);
            v += 1;
            if(v >= 20){

            }
            temValue = v.toString();
            jedis.set(key,temValue);
            //System.out.println("插入后的redis:"+key+" "+jedis.get(key));
        }

    }

    /**
     * 用于延长登录有效时间
     * key:userId
     * value:token
     * @param key
     * @param value
     */
    public void redisInLoginExtend(String key, String value){

    }


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
