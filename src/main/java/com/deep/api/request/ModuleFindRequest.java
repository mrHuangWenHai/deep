package com.deep.api.request;

import javax.validation.constraints.NotEmpty;

/**
 * create by zhongrui on 18-4-29.
 */
public class ModuleFindRequest {
    @NotEmpty
    private String auth;   //角色
    @NotEmpty
    private String mark;   //标志位 用于判断查询的模块
    private String isPass;  //通过是否查询筛选
    private int page;      //分页 页
    private int size;      //分页 条

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

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
