package com.deep.domain.model.sheepInfo;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

public class SalesRecordModel {
    private Long id;
    private Long startFactory;
    private Long endFactory;
    @NotBlank(message = "初始羊场不能为空！")
    private String startName;
    @NotBlank(message = "目标羊场不能为空！")
    private String endName;
    @NotBlank(message = "商标耳牌不能为空！")
    private String trademarkEarTag;
    private String immuneEarTag;
    private Timestamp salesTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStartFactory() {
        return startFactory;
    }

    public void setStartFactory(Long startFactory) {
        this.startFactory = startFactory;
    }

    public Long getEndFactory() {
        return endFactory;
    }

    public void setEndFactory(Long endFactory) {
        this.endFactory = endFactory;
    }

    public String getStartName() {
        return startName;
    }

    public void setStartName(String startName) {
        this.startName = startName;
    }

    public String getEndName() {
        return endName;
    }

    public void setEndName(String endName) {
        this.endName = endName;
    }

    public String getTrademarkEarTag() {
        return trademarkEarTag;
    }

    public void setTrademarkEarTag(String trademarkEarTag) {
        this.trademarkEarTag = trademarkEarTag;
    }

    public String getImmuneEarTag() {
        return immuneEarTag;
    }

    public void setImmuneEarTag(String immuneEarTag) {
        this.immuneEarTag = immuneEarTag;
    }

    public Timestamp getSalesTime() {
        return salesTime;
    }

    public void setSalesTime(Timestamp salesTime) {
        this.salesTime = salesTime;
    }

    @Override
    public String toString() {
        return "SalesRecordModel{" +
                "id=" + id +
                ", startFactory=" + startFactory +
                ", endFactory=" + endFactory +
                ", startName='" + startName + '\'' +
                ", endName='" + endName + '\'' +
                ", trademarkEarTag='" + trademarkEarTag + '\'' +
                ", immuneEarTag='" + immuneEarTag + '\'' +
                ", salesTime=" + salesTime +
                '}';
    }
}
