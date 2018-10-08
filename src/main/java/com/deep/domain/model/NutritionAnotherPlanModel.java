package com.deep.domain.model;

import java.sql.Timestamp;

public class NutritionAnotherPlanModel {
    // 记录基本信息
    private Long id;
    private Timestamp gmtCreate;
    private Timestamp gmtModified;
    private Timestamp gmtSupervised;

    // 羊场信息
    private Long factoryNum;
    private String factoryName;

    // 基本信息
    private String building;
    private String earTagFile;
    private Timestamp nutritionT;
    private Long quantity;
    private String average;
    private String period;
    private String water;

    // 精料相关信息
    private String materialA;
    private String materialM;
    private String materialO;
    private String materialWM;
    private String materialWO;

    // 粗料相关信息
    private String roughageP;
    private String roughageD;
    private String roughageO;
    private String roughageWP;
    private String roughageWD;
    private String roughageWO;

    // 全日量
    private String dayM;

    // 领料
    private String pickingM;
    private String pickingR;
    private String pickingO;

    // 备注
    private String remark;

    private Long operatorId;
    private String operatorName;
    private Long professorId;
    private String professorName;
    private Long supervisorId;
    private String supervisorName;

    private Byte ispassCheck;
    private String upassReason;
    private Byte ispassSup;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Timestamp getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Timestamp gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Timestamp getGmtSupervised() {
        return gmtSupervised;
    }

    public void setGmtSupervised(Timestamp gmtSupervised) {
        this.gmtSupervised = gmtSupervised;
    }

    public Long getFactoryNum() {
        return factoryNum;
    }

    public void setFactoryNum(Long factoryNum) {
        this.factoryNum = factoryNum;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getEarTagFile() {
        return earTagFile;
    }

    public void setEarTagFile(String earTagFile) {
        this.earTagFile = earTagFile;
    }

    public Timestamp getNutritionT() {
        return nutritionT;
    }

    public void setNutritionT(Timestamp nutritionT) {
        this.nutritionT = nutritionT;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public String getMaterialA() {
        return materialA;
    }

    public void setMaterialA(String materialA) {
        this.materialA = materialA;
    }

    public String getMaterialM() {
        return materialM;
    }

    public void setMaterialM(String materialM) {
        this.materialM = materialM;
    }

    public String getMaterialO() {
        return materialO;
    }

    public void setMaterialO(String materialO) {
        this.materialO = materialO;
    }

    public String getMaterialWM() {
        return materialWM;
    }

    public void setMaterialWM(String materialWM) {
        this.materialWM = materialWM;
    }

    public String getMaterialWO() {
        return materialWO;
    }

    public void setMaterialWO(String materialWO) {
        this.materialWO = materialWO;
    }

    public String getRoughageP() {
        return roughageP;
    }

    public void setRoughageP(String roughageP) {
        this.roughageP = roughageP;
    }

    public String getRoughageD() {
        return roughageD;
    }

    public void setRoughageD(String roughageD) {
        this.roughageD = roughageD;
    }

    public String getRoughageO() {
        return roughageO;
    }

    public void setRoughageO(String roughageO) {
        this.roughageO = roughageO;
    }

    public String getRoughageWP() {
        return roughageWP;
    }

    public void setRoughageWP(String roughageWP) {
        this.roughageWP = roughageWP;
    }

    public String getRoughageWD() {
        return roughageWD;
    }

    public void setRoughageWD(String roughageWD) {
        this.roughageWD = roughageWD;
    }

    public String getRoughageWO() {
        return roughageWO;
    }

    public void setRoughageWO(String roughageWO) {
        this.roughageWO = roughageWO;
    }

    public String getDayM() {
        return dayM;
    }

    public void setDayM(String dayM) {
        this.dayM = dayM;
    }

    public String getPickingM() {
        return pickingM;
    }

    public void setPickingM(String pickingM) {
        this.pickingM = pickingM;
    }

    public String getPickingR() {
        return pickingR;
    }

    public void setPickingR(String pickingR) {
        this.pickingR = pickingR;
    }

    public String getPickingO() {
        return pickingO;
    }

    public void setPickingO(String pickingO) {
        this.pickingO = pickingO;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public Long getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(Long supervisorId) {
        this.supervisorId = supervisorId;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public Byte getIspassCheck() {
        return ispassCheck;
    }

    public void setIspassCheck(Byte ispassCheck) {
        this.ispassCheck = ispassCheck;
    }

    public String getUpassReason() {
        return upassReason;
    }

    public void setUpassReason(String upassReason) {
        this.upassReason = upassReason;
    }

    public Byte getIspassSup() {
        return ispassSup;
    }

    public void setIspassSup(Byte ispassSup) {
        this.ispassSup = ispassSup;
    }

    @Override
    public String toString() {
        return "NutritionAnotherPlanModel{" +
                "id=" + id +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", gmtSupervised=" + gmtSupervised +
                ", factoryNum=" + factoryNum +
                ", factoryName='" + factoryName + '\'' +
                ", building='" + building + '\'' +
                ", earTagFile='" + earTagFile + '\'' +
                ", nutritionT=" + nutritionT +
                ", quantity=" + quantity +
                ", average='" + average + '\'' +
                ", period='" + period + '\'' +
                ", water='" + water + '\'' +
                ", materialA='" + materialA + '\'' +
                ", materialM='" + materialM + '\'' +
                ", materialO='" + materialO + '\'' +
                ", materialWM='" + materialWM + '\'' +
                ", materialWO='" + materialWO + '\'' +
                ", roughageP='" + roughageP + '\'' +
                ", roughageD='" + roughageD + '\'' +
                ", roughageO='" + roughageO + '\'' +
                ", roughageWP='" + roughageWP + '\'' +
                ", roughageWD='" + roughageWD + '\'' +
                ", roughageWO='" + roughageWO + '\'' +
                ", dayM='" + dayM + '\'' +
                ", pickingM='" + pickingM + '\'' +
                ", pickingR='" + pickingR + '\'' +
                ", pickingO='" + pickingO + '\'' +
                ", remark='" + remark + '\'' +
                ", operatorId=" + operatorId +
                ", operatorName='" + operatorName + '\'' +
                ", professorId=" + professorId +
                ", professorName='" + professorName + '\'' +
                ", supervisorId=" + supervisorId +
                ", supervisorName='" + supervisorName + '\'' +
                ", ispassCheck=" + ispassCheck +
                ", upassReason='" + upassReason + '\'' +
                ", ispassSup=" + ispassSup +
                '}';
    }
}
