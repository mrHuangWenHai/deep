package com.deep.domain.util;

import com.deep.api.response.Response;
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
    private String key;
    private String value;
    private int expireTime;


    public JedisUtil() {
    }

    /**
     * 构造函数 传入(String) key和(String) value时 直接存入redis中
     * 无过期时间
     *
     * @param key
     * @param value
     */
    public JedisUtil(String key, String value) {
        this.key = key;
        this.value = value;
        Jedis jedis = new Jedis("localhost");
        jedis.set(key, value);
    }

    /**
     * 构造函数 传入(String)key (String)value 和 (int)expireTime
     * 过期时间未expireTime
     *
     * @param key
     * @param value
     * @param expireTime
     */
    public JedisUtil(String key, String value, int expireTime) {
        this.expireTime = expireTime;
        this.key = key;
        this.value = value;
        Jedis jedis = new Jedis("localhost");
        jedis.set(key, value);
        jedis.expire(key, expireTime);
    }

    /**
     * 获取"factory_num+模块名+专家/监督员"对应value
     * 用于比对专家/监督员未完成任务
     * 超过50次(自定义)未操作时返回true
     * @param key
     */
    public boolean redisJudgeTime(String key){
        Jedis jedis = new Jedis("localhost");
        return Integer.parseInt(jedis.get(key)) >= 3;
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
    public void redisSaveProfessorSupervisorWorks(String key){
        Jedis jedis = new Jedis("localhost");
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

    //未解决:发送一次短信后 操作员继续插入 则会继续发送
    //理想方案:即使专家/监督员未完成任务 操作员在提交表格后 1天也最多发1条短信

    /**
     *
     * @param mobile_list
     * @param message
     */
    public boolean redisSendMessage(String mobile_list, String message){
        MobileAnnouncementModel mobileAnnouncementModel = new MobileAnnouncementModel(mobile_list,message);
        return true;
        /*if(manyMessageSendResult(mobileAnnouncementModel)){
            return new Response().addData("Success","");
        }else {
            return new Response().addData("Error","Send fail");
        }*/
    }

    /**
     * 验证过程
     * 用于查看单条短信发送后状态
     * 发送返回 成功:true 失败:false
     * @param mobileAnnouncementModel
     * @return
     */
    public Response oneMessageSendResult(MobileAnnouncementModel mobileAnnouncementModel){
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
    public boolean manyMessageSendResult(MobileAnnouncementModel mobileAnnouncementModel){
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
     * @param value
     */
    public void redisInLoginExtend(String key, String value){

    }

    /**
     * 获取redis中某一key的value
     * @param key
     * @return
     */
    public String getCertainKeyValue(String key){
        Jedis jedis = new Jedis("localhost");
        return jedis.get(key);
    }

    /**
     * 存储某一key的value至redis
     * @param key
     * @param value
     * @return
     */
    public void setCertainKeyValue(String key, String value){
        Jedis jedis = new Jedis("localhost");
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
    public void setCertainKeyValueWithExpireTime(String key, String value,int expireTime){
        Jedis jedis = new Jedis("localhost");
        jedis.set(key, value);
        jedis.expire(key,expireTime);
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
