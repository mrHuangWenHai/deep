package com.deep.domain.model;

import java.util.UUID;

/**
 * Interger:存储登录用户ID
 * identify:记录密码找回成功后状态
 */
public class TokenModel {
    private long userId;
    private String identify;
    private String token;    //随机生成UUID

    public TokenModel() {
    }

    public TokenModel(Long userId) {
        this.userId = userId;
        this.token = UUID.randomUUID().toString();
    }

    public TokenModel(String identify) {
        this.userId = userId;
        this.token = UUID.randomUUID().toString();
    }



    public TokenModel(String identify, String token) {
        this.identify = identify;
        this.token = token;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getIdentify() {
        return identify;
    }

    public void setIdentify(String identify) {
        this.identify = identify;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
