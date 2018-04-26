package com.deep.domain.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Talk {
    private Long t_id;
    @JSONField(name = "talker_id")
    private Long talker_id;
    @JSONField(name = "talker_name")
    private Long talker_name;
    @JSONField(name = "receiver_id")
    private Long receiver_id;
    @JSONField(name = "content")
    private String content;
    @JSONField(name = "start_time")
    private Date start_time;
    @JSONField(name = "isLink")
    private boolean isLink;
    @JSONField(name = "isHandle")
    private boolean isHandle;

    public boolean isLink() {
        return isLink;
    }

    public void setLink(boolean link) {
        isLink = link;
    }

    public Long getTalker_name() {
        return talker_name;
    }

    public void setTalker_name(Long talker_name) {
        this.talker_name = talker_name;
    }

    public Long getT_id() {
        return t_id;
    }

    public void setT_id(Long t_id) {
        this.t_id = t_id;
    }

    public Long getTalker_id() {
        return talker_id;
    }

    public void setTalker_id(Long talker_id) {
        this.talker_id = talker_id;
    }

    public Long getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(Long receiver_id) {
        this.receiver_id = receiver_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public boolean isHandle() {
        return isHandle;
    }

    public void setHandle(boolean handle) {
        isHandle = handle;
    }
}
