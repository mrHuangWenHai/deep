package com.deep.domain.model;

import com.alibaba.fastjson.annotation.JSONField;

public class Client {

    private Long id;
    @JSONField(name = "user_realname")
    private String user_realname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUser_realname() {
        return user_realname;
    }

    public void setUser_realname(String user_realname) {
        this.user_realname = user_realname;
    }
}
