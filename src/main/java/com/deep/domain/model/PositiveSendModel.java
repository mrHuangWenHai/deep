package com.deep.domain.model;

import javax.validation.constraints.NotEmpty;

/**
 * 用于主动给客户发送短信
 * 可输入号码或上传文件
 * create by zhongrui on 18-3-29.
 */
public class PositiveSendModel {
    @NotEmpty
    private String mobile_list;
    @NotEmpty
    private String message;

    public PositiveSendModel() {
    }

    public PositiveSendModel(String mobile_list, String message) {
        this.mobile_list = mobile_list;
        this.message = message;
    }

    public String getMobile_list() {
        return mobile_list;
    }

    public void setMobile_list(String mobile_list) {
        this.mobile_list = mobile_list;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
