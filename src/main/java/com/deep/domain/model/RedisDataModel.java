package com.deep.domain.model;

/**
 * 用于设置redis数据库中的内容
 * create by zhongrui on 18-3-29.
 */
public class RedisDataModel {
    private String message; //短信编辑内容
    private String expireTime;  //发短信间隔时间
    private String pressureTips;   //到达多少条后通知

    public RedisDataModel() {
    }

    public RedisDataModel(String message, String expireTime, String pressureTips) {
        this.message = message;
        this.expireTime = expireTime;
        this.pressureTips = pressureTips;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getPressureTips() {
        return pressureTips;
    }

    public void setPressureTips(String pressureTips) {
        this.pressureTips = pressureTips;
    }
}
