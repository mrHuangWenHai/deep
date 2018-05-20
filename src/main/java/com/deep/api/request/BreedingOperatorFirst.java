package com.deep.api.request;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * 配种产子档案第一阶段 操作员提交
 */
public class BreedingOperatorFirst {
    private Timestamp gmtCreate;
    private Timestamp gmtModify;
    @NotNull(message = "配种后待移栏栋不能为空")
    private String buildingAfterBreeding;
    @NotNull(message = "原住栏不能为空")
    private String buildingOld;
    @NotNull(message = "公羊商标耳牌不能为空")
    private String ramSheepTrademark;
    @NotNull(message = "母羊商标耳牌不能为空")
    private String eweSheepTrademark;
    @NotNull(message = "配种时间不能为空")
    private Timestamp breedingTime;
    @NotNull(message = "妊娠时间不能为空")
    private Timestamp pregnancyTime;
    @NotNull(message = "操作员操作时间不能为空")
    private Timestamp operatorTimeFirst;
    @NotNull(message = "操作员id不能为空")
    private Integer operatorIdFirst;
    @NotNull(message = "操作员姓名不能为空")
    private String operatorNameFirst;

    private String remarkFirst;

    // 羊场信息
    @NotNull(message = "羊场编号不能为空")
    private Integer factoryNumber;
    @NotNull(message = "羊场名称不能为空")
    private String factoryName;

    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Timestamp getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Timestamp gmtModify) {
        this.gmtModify = gmtModify;
    }

    public String getBuildingAfterBreeding() {
        return buildingAfterBreeding;
    }

    public void setBuildingAfterBreeding(String buildingAfterBreeding) {
        this.buildingAfterBreeding = buildingAfterBreeding;
    }

    public String getBuildingOld() {
        return buildingOld;
    }

    public void setBuildingOld(String buildingOld) {
        this.buildingOld = buildingOld;
    }

    public String getRamSheepTrademark() {
        return ramSheepTrademark;
    }

    public void setRamSheepTrademark(String ramSheepTrademark) {
        this.ramSheepTrademark = ramSheepTrademark;
    }

    public String getEweSheepTrademark() {
        return eweSheepTrademark;
    }

    public void setEweSheepTrademark(String eweSheepTrademark) {
        this.eweSheepTrademark = eweSheepTrademark;
    }

    public Timestamp getBreedingTime() {
        return breedingTime;
    }

    public void setBreedingTime(Timestamp breedingTime) {
        this.breedingTime = breedingTime;
    }

    public Timestamp getPregnancyTime() {
        return pregnancyTime;
    }

    public void setPregnancyTime(Timestamp pregnancyTime) {
        this.pregnancyTime = pregnancyTime;
    }

    public Timestamp getOperatorTimeFirst() {
        return operatorTimeFirst;
    }

    public void setOperatorTimeFirst(Timestamp operatorTimeFirst) {
        this.operatorTimeFirst = operatorTimeFirst;
    }

    public Integer getOperatorIdFirst() {
        return operatorIdFirst;
    }

    public void setOperatorIdFirst(Integer operatorIdFirst) {
        this.operatorIdFirst = operatorIdFirst;
    }

    public String getOperatorNameFirst() {
        return operatorNameFirst;
    }

    public void setOperatorNameFirst(String operatorNameFirst) {
        this.operatorNameFirst = operatorNameFirst;
    }

    public String getRemarkFirst() {
        return remarkFirst;
    }

    public void setRemarkFirst(String remarkFirst) {
        this.remarkFirst = remarkFirst;
    }

    public Integer getFactoryNumber() {
        return factoryNumber;
    }

    public void setFactoryNumber(Integer factoryNumber) {
        this.factoryNumber = factoryNumber;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }
}
