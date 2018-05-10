package com.deep.domain.model;

import com.alibaba.fastjson.annotation.JSONField;

public class Client {

    private Long id;
    @JSONField(name = "user_realname")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
