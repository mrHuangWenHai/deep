package com.deep.api.request;

import javax.validation.constraints.NotNull;

public class NutritionAddRequest {
    // 羊场的基本信息
    private Long factoryNum;
    private String factoryName;

    // 用户填写的基本信息
    @NotNull(message = "栏栋不能为空")
    private String building;
    @NotNull(message = "商标耳牌号不能为空")
    private String eartagFile;
    @NotNull(message = "使用日期不能为空")
    private String nutritionT;
    @NotNull(message = "羊只数不能为空")
    private Integer quantity;
    @NotNull(message = "羊只均重不能为空")
    private Integer average;
    @NotNull(message = "营养阶段不能为空")
    private String period;
    @NotNull(message = "饮水字段不能为空")
    private String water;

    // 精料相关字段
    private String materialA;
    private String materialM;
    private String materialO;
    private String materialWM;
    private String materialWO;

    // 粗料相关字段
    private String roughageD;
    private String roughageO;
    private String roughageP;
    private String roughageWD;
    private String roughageWO;
    private String roughageWP;

    // 全日量用量
    private String dayM;

    // 领料总量
    private String pickingM;
    private String pickingO;
    private String pickingR;

    // 备注
    private String remark;

    private Long operatorId;
    private String operatorName;

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getEartagFile() {
        return eartagFile;
    }

    public void setEartagFile(String eartagFile) {
        this.eartagFile = eartagFile;
    }

    public String getNutritionT() {
        return nutritionT;
    }

    public void setNutritionT(String nutritionT) {
        this.nutritionT = nutritionT;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getAverage() {
        return average;
    }

    public void setAverage(Integer average) {
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

    public String getDayM() {
        return dayM;
    }

    public void setDayM(String dayM) {
        this.dayM = dayM;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public Long getFactoryNum() {
        return factoryNum;
    }

    public void setFactoryNum(Long factoryNum) {
        this.factoryNum = factoryNum;
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

    public String getPickingM() {
        return pickingM;
    }

    public void setPickingM(String pickingM) {
        this.pickingM = pickingM;
    }

    public String getPickingO() {
        return pickingO;
    }

    public void setPickingO(String pickingO) {
        this.pickingO = pickingO;
    }

    public String getPickingR() {
        return pickingR;
    }

    public void setPickingR(String pickingR) {
        this.pickingR = pickingR;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public String getRoughageP() {
        return roughageP;
    }

    public void setRoughageP(String roughageP) {
        this.roughageP = roughageP;
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

    public String getRoughageWP() {
        return roughageWP;
    }

    public void setRoughageWP(String roughageWP) {
        this.roughageWP = roughageWP;
    }

    @Override
    public String toString() {
        return "NutritionAddRequest{" +
                "building='" + building + '\'' +
                ", eartagFile='" + eartagFile + '\'' +
                ", nutritionT='" + nutritionT + '\'' +
                ", quantity=" + quantity +
                ", average=" + average +
                ", period='" + period + '\'' +
                ", water='" + water + '\'' +
                ", dayM='" + dayM + '\'' +
                ", factoryName='" + factoryName + '\'' +
                ", factoryNum=" + factoryNum +
                ", materialA='" + materialA + '\'' +
                ", materialM='" + materialM + '\'' +
                ", materialO='" + materialO + '\'' +
                ", materialWM='" + materialWM + '\'' +
                ", materialWO='" + materialWO + '\'' +
                ", operatorId=" + operatorId +
                ", operatorName='" + operatorName + '\'' +
                ", pickingM='" + pickingM + '\'' +
                ", pickingO='" + pickingO + '\'' +
                ", pickingR='" + pickingR + '\'' +
                ", remark='" + remark + '\'' +
                ", roughageD='" + roughageD + '\'' +
                ", roughageO='" + roughageO + '\'' +
                ", roughageP='" + roughageP + '\'' +
                ", roughageWD='" + roughageWD + '\'' +
                ", roughageWO='" + roughageWO + '\'' +
                ", roughageWP='" + roughageWP + '\'' +
                '}';
    }
}
