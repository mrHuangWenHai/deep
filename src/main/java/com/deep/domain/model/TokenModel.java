package com.deep.domain.model;

import java.util.UUID;

public class TokenModel {
    private Integer userId;
    private String token;    //随机生成UUID

    public TokenModel() {
    }

    public TokenModel(Integer userId) {
        this.userId = userId;
        this.token = UUID.randomUUID().toString();
    }

    public TokenModel(Integer userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
