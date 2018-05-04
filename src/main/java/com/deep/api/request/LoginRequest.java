package com.deep.api.request;

import javax.validation.constraints.NotNull;

public class LoginRequest {

    @NotNull(message = "用户名不能为空")
    private String username;
    @NotNull(message = "密码不能为空")
    private String password;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginRequest{" +
            "username='" + username + '\'' +
            ", password='" + password + '\'' +
            '}';
    }
}
