package com.deep.api.request;

import javax.validation.constraints.*;

public class RegisterRequest {
    @NotBlank(message = "用户名不能为空!")
    @Pattern(regexp = "^[0-9a-zA-Z]+$", message = "用户名只能由a-z以及数字组成")
    @Size(min = 4, max = 20, message = "用户名最小长度为4, 最大长度为20")
    private String username;

    private String password;

    @Pattern(regexp = "(^$|(^[1][3-9][0-9]{9}$)|(^[1-9]{1}[0-9]{5,8}))", message = "号码格式错误")
    private String userTelephone;

    @NotBlank(message = "必须设置密保问题1")
    private String question_1;    //找回密码问题1

    @NotBlank(message = "必须设置密保问题1的答案")
    private String answer_1;     //找回密码答案1

    @NotBlank(message = "必须设置密保问题2")
    private String question_2;

    @NotBlank(message = "必须设置密保问题2的答案")
    private String answer_2;

    @NotBlank(message = "必须设置密保问题3")
    private String question_3;

    @NotBlank(message = "必须设置密保问题3的答案")
    private String answer_3;

    @Email(message = "邮箱格式不正确")
    private String userEmail;

    @Pattern(regexp = "(^$|(^[1-9][0-9]{4,9}$))", message = "QQ号格式错误")
    private String qq;

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

    public String getUserTelephone() {
        return userTelephone;
    }

    public void setUserTelephone(String userTelephone) {
        this.userTelephone = userTelephone;
    }

    public String getQuestion_1() {
        return question_1;
    }

    public void setQuestion_1(String question_1) {
        this.question_1 = question_1;
    }

    public String getAnswer_1() {
        return answer_1;
    }

    public void setAnswer_1(String answer_1) {
        this.answer_1 = answer_1;
    }

    public String getQuestion_2() {
        return question_2;
    }

    public void setQuestion_2(String question_2) {
        this.question_2 = question_2;
    }

    public String getAnswer_2() {
        return answer_2;
    }

    public void setAnswer_2(String answer_2) {
        this.answer_2 = answer_2;
    }

    public String getQuestion_3() {
        return question_3;
    }

    public void setQuestion_3(String question_3) {
        this.question_3 = question_3;
    }

    public String getAnswer_3() {
        return answer_3;
    }

    public void setAnswer_3(String answer_3) {
        this.answer_3 = answer_3;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Override
    public String toString() {
        return "RegisterRequest{" +
            "username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", userTelephone='" + userTelephone + '\'' +
            ", question_1='" + question_1 + '\'' +
            ", answer_1='" + answer_1 + '\'' +
            ", question_2='" + question_2 + '\'' +
            ", answer_2='" + answer_2 + '\'' +
            ", question_3='" + question_3 + '\'' +
            ", answer_3='" + answer_3 + '\'' +
            ", userEmail='" + userEmail + '\'' +
            ", qq='" + qq + '\'' +
            '}';
    }
}
