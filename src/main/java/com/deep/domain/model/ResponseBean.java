package com.deep.domain.model;

public class ResponseBean {
    private String message;
    private String order;
    private String talk_id;
    private String name;

    public ResponseBean(String message, String order, String talk_id, String name) {
        this.message = message;
        this.order = order;
        this.talk_id = talk_id;
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getTalk_id() {
        return talk_id;
    }

    public void setTalk_id(String talk_id) {
        this.talk_id = talk_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
