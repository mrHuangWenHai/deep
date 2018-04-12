package com.deep.domain.util;

import com.deep.api.response.Response;
import com.deep.api.response.Responses;
import com.deep.domain.model.MobileAnnouncementModel;
import org.json.JSONException;
import org.json.JSONObject;
import redis.clients.jedis.Jedis;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * create by zhongrui on 18-3-21.
 * Redis工具类
 * 用于操作与redis相关的数据行为
 */
public class JedisUtil {


    private static Jedis jedis = new Jedis("localhost");

    public JedisUtil() {
    }

    /**
     * 获取"factory_num+模块名+专家/监督员"对应value
     * 用于比对专家/监督员未完成任务
     * 超过50次(自定义)未操作时返回true
     * @param key
     */
    public static boolean redisJudgeTime(String key){
        int i = Integer.parseInt(jedis.get(key));
        int j = Integer.parseInt(jedis.get("PressureTips"));
        return i > j;
    }




    /**
     *
     * 用户存储专家/监督员的剩余工作
     * key:"factory_num+模块名+专家/监督员"
     * value:未审核条数
     *
     * 功能：在操作员添加了一条数据后,redis中对应数据+1
     * @param key
     */
    public static void redisSaveProfessorSupervisorWorks(String key){
        String temValue = jedis.get(key);
        if (temValue == null){
            jedis.set(key,"1");
        }else {
            //System.out.println("断点1");
            Integer v = Integer.parseInt(temValue);
            v += 1;
            //System.out.println("before :"+"redis key:"+key+" redis value:"+jedis.get(key));
            temValue = v.toString();
            jedis.set(key,temValue);
        }

        //System.out.println("after :"+"redis key:"+key+" redis value:"+jedis.get(key));
    }

    /**
     * key value同上
     * 功能：在专家/审核员 处理了一条数据后,redis中对应数据-1
     * @param key
     */
    public static boolean redisCancelProfessorSupervisorWorks(String key){
        String temValue = jedis.get(key);
        if (temValue == null || "0".equals(temValue)){
            return false;
        }else {
            //System.out.println("断点1");
            Integer v = Integer.parseInt(temValue);
            v -= 1;
            //System.out.println("before :"+"redis key:"+key+" redis value:"+jedis.get(key));
            temValue = v.toString();
            jedis.set(key,temValue);
            return true;
        }
        //System.out.println("after :"+"redis key:"+key+" redis value:"+jedis.get(key));
    }

    /**
     *
     * @param mobile_list
     * @param message
     */
    public static boolean redisSendMessage(String mobile_list, String message){
        MobileAnnouncementModel mobileAnnouncementModel = new MobileAnnouncementModel(mobile_list,message);

        //发送成功 返回true
        if(manyMessageSendResult(mobileAnnouncementModel)){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 验证过程
     * 用于查看单条短信发送后状态
     * 发送返回 成功:true 失败:false
     * @param mobileAnnouncementModel
     * @return
     */
    public static Response oneMessageSendResult(MobileAnnouncementModel mobileAnnouncementModel){
        String httpResponse =  mobileAnnouncementModel.testSendSingle();
        try {
            JSONObject jsonObj = new JSONObject( httpResponse );
            int error_code = jsonObj.getInt("error");
            String error_msg = jsonObj.getString("msg");
            if(error_code==0){
                System.out.println("Send message success.");
                return new Response().addData("Success","");
            }else{
                System.out.println("Send message failed,code is "+error_code+",msg is "+error_msg);
            }
        } catch (JSONException ex) {
            Logger.getLogger(MobileAnnouncementModel.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MobileAnnouncementModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Response().addData("Error","Send Error");
    }


    /**
     * 用于查看批量短信发送后状态
     * 发送返回 成功:true 失败:false
     * @param mobileAnnouncementModel
     * @return
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
            Logger.getLogger(MobileAnnouncementModel.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(MobileAnnouncementModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    /**
     * 用于延长登录有效时间
     * key:userId
     * value:token
     * @param key
     * @param expireTime
     */
    public static void expireCertainKey(String key, int expireTime){
        jedis.expire(key, expireTime);
    }

    /**
     * 获取redis中某一key的value
     * @param key
     * @return
     */
    public static String getCertainKeyValue(String key){
        return jedis.get(key);
    }

    /**
     * 存储某一key的value至redis
     * @param key
     * @param value
     * @return
     */
    public static void setCertainKeyValue(String key, String value){
        jedis.set(key, value);
    }


    /**
     * 存储某一key的value至redis
     * 存在过期时间
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    public static void setCertainKeyValueWithExpireTime(String key, String value,int expireTime){
        jedis.set(key, value);
        jedis.expire(key,expireTime);
    }

}