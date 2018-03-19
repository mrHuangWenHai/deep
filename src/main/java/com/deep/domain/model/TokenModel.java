package com.deep.domain.model;

import java.util.UUID;

public class TokenModel {
    private Long userId;
    private String token;    //随机生成UUID

    public TokenModel() {
    }

    public TokenModel(Long userId) {
        this.userId = userId;
        this.token = UUID.randomUUID().toString();
    }

    public TokenModel(Long userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
