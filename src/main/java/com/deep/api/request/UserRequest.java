package com.deep.api.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRequest {
    @NotBlank(message = "用户名不能为空!")
    @Pattern(regexp = "^[0-9a-zA-Z]+$", message = "用户名只能由a-z以及数字组成")
    @Size(min = 4, max = 20, message = "用户名最小长度为4, 最大长度为20")
    private String username;

    private String password;

    @NotBlank(message = "不能为空")
    private String realname;

    @Pattern(regexp = "(^$|(^[1][3-9][0-9]{9}$)|(^[1-9]{1}[0-9]{5,8}))", message = "号码格式错误")
    private String telephone;

    private byte flag;

    @NotBlank(message = "不能为空")
    private String factoryName;

    private Long factoryId;

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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public byte getFlag() {
        return flag;
    }

    public void setFlag(byte flag) {
        this.flag = flag;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public Long getFactoryId() {
        return factoryId;
    }

    public void setFactoryId(Long factoryId) {
        this.factoryId = factoryId;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    @Override
    public String toString() {
        return "UserRequest{" +
            "username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", realname='" + realname + '\'' +
            ", telephone='" + telephone + '\'' +
            ", flag=" + flag +
            ", factoryName='" + factoryName + '\'' +
            ", factoryId=" + factoryId +
            '}';
    }
}
