package com.deep.api.request;

import java.sql.Timestamp;

public class DeadSheepRequest {
    private Long id;
    private String method;
    private String reason;
    private Byte dead;
    private Timestamp date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Byte getDead() {
        return dead;
    }

    public void setDead(Byte dead) {
        this.dead = dead;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "DeadSheepRequest{" +
                "id=" + id +
                ", method='" + method + '\'' +
                ", reason='" + reason + '\'' +
                ", dead=" + dead +
                ", date=" + date +
                '}';
    }
}
