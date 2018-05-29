package com.deep.domain.util;

import com.deep.api.Utils.RedisPool;
import com.deep.domain.model.MobileAnnouncementModel;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

/**
 * create by zhongrui on 18-3-21.
 * Redis工具类
 * 用于操作与redis相关的数据行为
 */
public class JedisUtil {
    private final static Logger logger = LoggerFactory.getLogger(JedisUtil.class);
    private final static String pressureTips = "50";
    private final static String message = "请尽快完成任务";
    private final static String expireTime = "3";

    public JedisUtil() {

    }

    /**
     * 获取"factory_num+模块名+专家/监督员"对应value
     * 用于比对专家/监督员未完成任务
     * 超过50次(自定义)未操作时返回true
     * @param key key
     */
    public static boolean redisJudgeTime(String key){
        Jedis jedis = RedisPool.getJedis();
        if (jedis == null) {
            logger.info("jedis == null");
            return false;
        }
        try {
            if (!jedis.exists(key)) {
                return false;
            } else {
                String valuei = jedis.get(key);
                String valuej = jedis.get("PressureTips");

                if (valuei == null || valuei.equals("")) {
                    jedis.set(key,"0");
                }

                if (valuej == null || valuej.equals("")) {
                    jedis.set("PressureTips",pressureTips);
                }

                int i = Integer.parseInt(jedis.get(key));
                int j = Integer.parseInt(jedis.get("PressureTips"));
                return i > j;
            }
        } catch (Exception ex) {
            logger.info("获取异常！", ex.getMessage());
        } finally {
            try {
                jedis.close();
            } catch (Exception ex) {
                logger.info("关闭异常 {}",ex.getMessage());
            }
        }
        return false;
    }




    /**
     *
     * 用户存储专家/监督员的剩余工作
     * key:"factory_num+模块名+专家/监督员"
     * value:未审核条数
     *
     * 功能：在操作员添加了一条数据后,redis中对应数据+1
     * @param key key
     */
    public static void redisSaveProfessorSupervisorWorks(String key) {
        Jedis jedis = RedisPool.getJedis();
        if (jedis == null) {
            logger.info("jedis == null");
            return;
        }
        try {
            if (!jedis.exists(key)) {
            } else {
                String temValue = jedis.get(key);
                if (temValue == null) {
                    jedis.set(key,"1");
                } else {

                    Integer v = Integer.parseInt(temValue);
                    v += 1;

                    temValue = v.toString();
                    jedis.set(key,temValue);
                }
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

        //System.out.println("after :"+"redis key:"+key+" redis value:"+jedis.get(key));
    }

    /**
     * key value同上
     * 功能：在专家/审核员 处理了一条数据后,redis中对应数据-1
     * @param key key
     */
    public static boolean redisCancelProfessorSupervisorWorks(String key) {
        System.out.println("hehehehhehehe");
        Jedis jedis = RedisPool.getJedis();
        System.out.println("lalalalalalaa");
        if (jedis == null) {
            logger.info("jedis == null");
            return false;
        }
        try {
            if (!jedis.exists(key)) {
                return false;
            } else {
                String temValue = jedis.get(key);
                if (temValue == null || "0".equals(temValue)) {
                    return false;
                }else {
                    //System.out.println("断点1");
                    Integer v = Integer.parseInt(temValue);
                    v -= 1;
                    System.out.println("before :"+"redis key:"+key+" redis value:"+jedis.get(key));
                    temValue = v.toString();
                    jedis.set(key, temValue);
                    return true;
                }
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
        return false;
        //System.out.println("after :"+"redis key:"+key+" redis value:"+jedis.get(key));
    }

    /**
     *
     * @param mobile_list 电话字符串
     * @param message 短信内容
     */
    public static boolean redisSendMessage(String mobile_list, String message){
        MobileAnnouncementModel mobileAnnouncementModel = new MobileAnnouncementModel(mobile_list,message);
        //发送成功 返回true
        return manyMessageSendResult(mobileAnnouncementModel);
    }

    /**
     * 验证过程
     * 用于查看单条短信发送后状态
     * 发送返回 成功:true 失败:false
     * @param mobileAnnouncementModel 短信类
     * @return 发送结果
     */
    public static boolean oneMessageSendResult(MobileAnnouncementModel mobileAnnouncementModel){
        String httpResponse =  mobileAnnouncementModel.testSendSingle();
        try {
            JSONObject jsonObj = new JSONObject( httpResponse );
            int error_code = jsonObj.getInt("error");
            String error_msg = jsonObj.getString("msg");
            if(error_code==0){
                System.out.println("Send message success.");
                return true;
            }else{
                System.out.println("Send message failed,code is "+error_code+",msg is "+error_msg);
            }
        } catch (JSONException ex) {
        //    Logger.getLogger(MobileAnnouncementModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        httpResponse =  mobileAnnouncementModel.testStatus();
        try {
            JSONObject jsonObj = new JSONObject( httpResponse );
            int error_code = jsonObj.getInt("error");
            if( error_code == 0 ){
                int deposit = jsonObj.getInt("deposit");
                System.out.println("Fetch deposit success :"+deposit);
            }else{
                String error_msg = jsonObj.getString("msg");
                System.out.println("Fetch deposit failed,code is "+error_code+",msg is "+error_msg);
            }
        } catch (JSONException ex) {
           // Logger.getLogger(MobileAnnouncementModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }


    /**
     * 用于查看批量短信发送后状态
     * 发送返回 成功:true 失败:false
     * @param mobileAnnouncementModel 短信类
     * @return 发送结果
     */
    public static boolean manyMessageSendResult(MobileAnnouncementModel mobileAnnouncementModel){
        String httpResponse =  mobileAnnouncementModel.testSendMany();
        try {
            JSONObject jsonObj = new JSONObject( httpResponse );
            int error_code = jsonObj.getInt("error");
            String error_msg = jsonObj.getString("msg");
            if(error_code==0){
                System.out.println("Send message success.");
                return true;
            }else{
                System.out.println("Send message failed,code is "+error_code+",msg is "+error_msg);
            }
        } catch (JSONException ex) {
           // Logger.getLogger(MobileAnnouncementModel.class.getName()).log(Level.SEVERE, null, ex);
        }

        httpResponse =  mobileAnnouncementModel.testStatus();
        try {
            JSONObject jsonObj = new JSONObject( httpResponse );
            int error_code = jsonObj.getInt("error");
            if( error_code == 0 ){
                int deposit = jsonObj.getInt("deposit");
                System.out.println("Fetch deposit success :"+deposit);
            }else{
                String error_msg = jsonObj.getString("msg");
                System.out.println("Fetch deposit failed,code is "+error_code+",msg is "+error_msg);
            }
        } catch (JSONException ex) {
          //  Logger.getLogger(MobileAnnouncementModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }


    /**
     * 获取redis中某一key的value
     * @param key key
     * @return redis中对应key的value
     */
    public static String getCertainKeyValue(String key) {
        Jedis jedis = RedisPool.getJedis();
        if (jedis == null) {
            logger.info("jedis == null");
            return null;
        }
        try {
            if (!jedis.exists(key)) {
                return null;
            } else {
                String message = jedis.get(key);
                if (message == null || message.length() == 0) {
                    if (key.equals("Message")) {
                        jedis.set(key, JedisUtil.message);
                    } else if (key.equals("ExpireTime")) {
                        jedis.set(key, JedisUtil.expireTime);
                    }
                }
                return jedis.get(key);
            }
        }  catch (Exception e ) {
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

    /**
     * 存储某一key的value至redis
     * @param key key
     * @param value value
     */
    public static void setCertainKeyValue(String key, String value){
        Jedis jedis = RedisPool.getJedis();
        if (jedis == null) {
            logger.info("jedis == null");
            return ;
        }
        try {
            if (!jedis.exists(key)) {
            } else {
                jedis.set(key, value);
            }
        } catch (Exception ex) {
            logger.info("获取异常{}", ex.getMessage());
        } finally {
            try {
                jedis.close();
            } catch (Exception e) {
                logger.info("关闭异常 {}",e.getMessage());
            }
        }
    }


    /**
     * 存储某一key的value至redis
     * 存在过期时间
     * @param key key
     * @param value value
     * @param expireTime 有效时间
     */
    public static void setCertainKeyValueWithExpireTime(String key, String value,int expireTime){
        Jedis jedis = RedisPool.getJedis();
        if (jedis == null) {
            logger.info("jedis == null");
            return ;
        }
        try {
            if (!jedis.exists(key)) {
            } else {
                jedis.set(key, value);
                jedis.expire(key,expireTime);
            }
        } catch (Exception ex) {
            logger.info("获取异常 {}", ex.getMessage());
        } finally {
            try {
                jedis.close();
            } catch (Exception e) {
                logger.info("关闭异常 {}",e.getMessage());
            }

        }
    }
}
