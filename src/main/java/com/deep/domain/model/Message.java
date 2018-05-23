package com.deep.domain.model;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;
import java.util.regex.Pattern;

public class Message implements Serializable {
    private Integer id;

    private static final long serialVersionUID = 1L;

    @NotBlank(message = "用户名不可为空")
    private String username;

    @NotBlank(message = "联系方式不可为空")
    private String contact;

    @NotBlank(message = "留言不可为空")
    private String message;

    private Date inserttime;

    private String tag;

    @NotBlank(message = "态度不可为空")
    private String attitude;

    @NotBlank(message = "购买意向不可为空")
    private String intention;

    private String sTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String eTime;

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    private Integer messageId;

    private Integer pageNumb = 0;

    private Integer limit = 10;

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    private Integer size;

    public String getsTime() {
        return sTime;
    }

    public void setsTime(String sTime) {
        this.sTime = sTime;
    }

    public String geteTime() {
        return eTime;
    }

    public void seteTime(String eTime) {
        this.eTime = eTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public Date getInserttime() {
        return inserttime;
    }

    public void setInserttime(Date inserttime) {
        this.inserttime = inserttime;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag == null ? null : tag.trim();
    }

    public String getAttitude() {
        return attitude;
    }

    public void setAttitude(String attitude) {
        this.attitude = attitude == null ? null : attitude.trim();
    }

    public String getIntention() {
        return intention;
    }

    public void setIntention(String intention) {
        this.intention = intention == null ? null : intention.trim();
    }


    public Integer getPageNumb() {
        return pageNumb;
    }

    public void setPageNumb(Integer pageNumb) {
        this.pageNumb = pageNumb;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    //匹配手机号
    public static boolean isMobile(String mobile) {
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$";
        return Pattern.matches(regex, mobile);
    }
    //匹配邮箱
    public static boolean isEmail(String email){
        String regex = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$";
        return Pattern.matches(regex, email);
    }

    @Override
    public String toString() {
        return "Message{" +
            "id=" + id +
            ", username='" + username + '\'' +
            ", contact='" + contact + '\'' +
            ", message='" + message + '\'' +
            ", inserttime=" + inserttime +
            ", tag='" + tag + '\'' +
            ", attitude='" + attitude + '\'' +
            ", intention='" + intention + '\'' +
            ", sTime=" + sTime +
            ", eTime=" + eTime +
            ", messageId=" + messageId +
            ", pageNumb=" + pageNumb +
            ", limit=" + limit +
            '}';
    }
}
