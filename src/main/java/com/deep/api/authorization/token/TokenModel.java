package com.deep.api.authorization.token;

import java.util.UUID;

/**
 * Token的Model类, 可以增加字段以提高安全性, 例如时间戳, URL签名
 */
public class TokenModel {
    // 用户ID
    private long userId;
    // 随机生成的uuid
    private String token;

    public TokenModel () {

    }
    public TokenModel(long userId, String roleInt, String flag) {
        this.userId = userId;
        this.token = UUID.randomUUID().toString() + ":" + roleInt + ":" + flag;
    }

    public TokenModel(Long userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public long getUserId() {
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

    @Override
    public String toString() {
        return "TokenModel{" +
            "userId=" + userId +
            ", token='" + token + '\'' +
            '}';
    }
}
