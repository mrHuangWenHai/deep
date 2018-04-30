package com.deep.api.request;

import javax.validation.constraints.NotEmpty;

/**
 * create by zhongrui on 18-4-29.
 */
public class ModuleUpdateRequest {
    @NotEmpty
    private long id; //id
    @NotEmpty
    private String auth;   //角色
    @NotEmpty
    private String mark;   //标志位 用于判断查询的模块
    @NotEmpty
    private String isPass;  //是否通过
    private String unpassReason;   //未通过原因 监督员不填
    @NotEmpty
    private String name;    //专家名/监督员名

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getIsPass() {
        return isPass;
    }

    public void setIsPass(String isPass) {
        this.isPass = isPass;
    }

    public String getUnpassReason() {
        return unpassReason;
    }

    public void setUnpassReason(String unpassReason) {
        this.unpassReason = unpassReason;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
