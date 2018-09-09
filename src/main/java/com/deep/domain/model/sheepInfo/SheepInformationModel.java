package com.deep.domain.model.sheepInfo;

import java.sql.Timestamp;

public class SheepInformationModel {
    private Long id;
    private Long factory;
    private Long buildingColumn;

    private Integer building;
    private Integer col;

    private String type;
    private String trademarkEarTag;
    private String immuneEarTag;
    private Byte dead;
    private String reason;
    private String method;
    private Timestamp date;
    private Byte sale;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFactory() {
        return factory;
    }

    public void setFactory(Long factory) {
        this.factory = factory;
    }

    public Long getBuildingColumn() {
        return buildingColumn;
    }

    public void setBuildingColumn(Long buildingColumn) {
        this.buildingColumn = buildingColumn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public Byte getDead() {
        return dead;
    }

    public void setDead(Byte dead) {
        this.dead = dead;
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

    public Byte getSale() {
        return sale;
    }

    public void setSale(Byte sale) {
        this.sale = sale;
    }

    public Integer getBuilding() {
        return building;
    }

    public void setBuilding(Integer building) {
        this.building = building;
    }

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }

    @Override
    public String toString() {
        return "SheepInformationModel{" +
                "id=" + id +
                ", factory=" + factory +
                ", buildingColumn=" + buildingColumn +
                ", type='" + type + '\'' +
                ", trademarkEarTag='" + trademarkEarTag + '\'' +
                ", immuneEarTag='" + immuneEarTag + '\'' +
                ", dead=" + dead +
                ", reason='" + reason + '\'' +
                ", method='" + method + '\'' +
                ", date=" + date +
                ", sale=" + sale +
                '}';
    }
}
