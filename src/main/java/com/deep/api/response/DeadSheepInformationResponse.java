package com.deep.api.response;

import java.sql.Timestamp;

public class DeadSheepInformationResponse {
    private String trademarkEarTag;
    private String reason;
    private String method;
    private Timestamp date;

    public String getTrademarkEarTag() {
        return trademarkEarTag;
    }

    public void setTrademarkEarTag(String trademarkEarTag) {
        this.trademarkEarTag = trademarkEarTag;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "DeadSheepInformationResponse{" +
                "trademarkEarTag='" + trademarkEarTag + '\'' +
                ", reason='" + reason + '\'' +
                ", method='" + method + '\'' +
                ", date=" + date +
                '}';
    }
}
