package com.deep.domain.model;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable {
    private Integer id;

    @NotNull(message = "用户名不可为空")
    private String username;

    @NotNull(message = "联系方式不可为空")
    private String contact;

    @NotNull(message = "留言不可为空")
    private String message;

    private Date inserttime;

    @NotNull(message = "标签不可为空")
    private String tag;

    @NotNull(message = "态度不可为空")
    private String attitude;

    @NotNull(message = "购买意向不可为空")
    private String intention;

    private static final long serialVersionUID = 1L;

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
}