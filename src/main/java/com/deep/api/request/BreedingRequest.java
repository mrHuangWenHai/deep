package com.deep.api.request;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class BreedingRequest {
    private Timestamp gmtCreate;
    private Timestamp gmtModify;
    @NotNull(message = "配种时间不能为空")
    private Timestamp breedingTime;
    private String buildingAfterBreeding;
    @NotNull(message = "母羊商标耳牌不能为空")
    private String ramSheepTrademark;
    @NotNull(message = "公羊商标耳牌不能为空")
    private String eweSheepTrademark;
    private Byte manageFlag;
    private Timestamp manageAverageTime;
    private Timestamp nutritionBeforePregnancy;
    private String isPregnancy;
    private Timestamp nutritionAfterPregnancy;
    private String prenatalImmunityType;
    private String prenatalImmunityTime;
    private String buildingToBeRelocated;
    private Timestamp nutritionBeforeLambing;
    private Timestamp lambingTime;
    private Integer lambingNumber;
    private Timestamp nutritionBreastFeeding;
    private Timestamp nutritionInsteadBreastFeeding;
    private Timestamp nutritionBeforeCutBreastFeeding;
    private Timestamp nutritionCutBreastFeeding;

    private String remark;
    // 羊场信息
    private Integer factoryNumber;
    private String factoryName;

    private Timestamp operatorTime;
    private Integer operatorId;
    private String operatorName;

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

    public Timestamp getBreedingTime() {
        return breedingTime;
    }

    public void setBreedingTime(Timestamp breedingTime) {
        this.breedingTime = breedingTime;
    }

    public String getBuildingAfterBreeding() {
        return buildingAfterBreeding;
    }

    public void setBuildingAfterBreeding(String buildingAfterBreeding) {
        this.buildingAfterBreeding = buildingAfterBreeding;
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

    public Byte getManageFlag() {
        return manageFlag;
    }

    public void setManageFlag(Byte manageFlag) {
        this.manageFlag = manageFlag;
    }

    public Timestamp getManageAverageTime() {
        return manageAverageTime;
    }

    public void setManageAverageTime(Timestamp manageAverageTime) {
        this.manageAverageTime = manageAverageTime;
    }

    public Timestamp getNutritionBeforePregnancy() {
        return nutritionBeforePregnancy;
    }

    public void setNutritionBeforePregnancy(Timestamp nutritionBeforePregnancy) {
        this.nutritionBeforePregnancy = nutritionBeforePregnancy;
    }

    public String getIsPregnancy() {
        return isPregnancy;
    }

    public void setIsPregnancy(String isPregnancy) {
        this.isPregnancy = isPregnancy;
    }

    public Timestamp getNutritionAfterPregnancy() {
        return nutritionAfterPregnancy;
    }

    public void setNutritionAfterPregnancy(Timestamp nutritionAfterPregnancy) {
        this.nutritionAfterPregnancy = nutritionAfterPregnancy;
    }

    public String getPrenatalImmunityType() {
        return prenatalImmunityType;
    }

    public void setPrenatalImmunityType(String prenatalImmunityType) {
        this.prenatalImmunityType = prenatalImmunityType;
    }

    public String getPrenatalImmunityTime() {
        return prenatalImmunityTime;
    }

    public void setPrenatalImmunityTime(String prenatalImmunityTime) {
        this.prenatalImmunityTime = prenatalImmunityTime;
    }

    public String getBuildingToBeRelocated() {
        return buildingToBeRelocated;
    }

    public void setBuildingToBeRelocated(String buildingToBeRelocated) {
        this.buildingToBeRelocated = buildingToBeRelocated;
    }

    public Timestamp getNutritionBeforeLambing() {
        return nutritionBeforeLambing;
    }

    public void setNutritionBeforeLambing(Timestamp nutritionBeforeLambing) {
        this.nutritionBeforeLambing = nutritionBeforeLambing;
    }

    public Timestamp getLambingTime() {
        return lambingTime;
    }

    public void setLambingTime(Timestamp lambingTime) {
        this.lambingTime = lambingTime;
    }

    public Integer getLambingNumber() {
        return lambingNumber;
    }

    public void setLambingNumber(Integer lambingNumber) {
        this.lambingNumber = lambingNumber;
    }

    public Timestamp getNutritionBreastFeeding() {
        return nutritionBreastFeeding;
    }

    public void setNutritionBreastFeeding(Timestamp nutritionBreastFeeding) {
        this.nutritionBreastFeeding = nutritionBreastFeeding;
    }

    public Timestamp getNutritionInsteadBreastFeeding() {
        return nutritionInsteadBreastFeeding;
    }

    public void setNutritionInsteadBreastFeeding(Timestamp nutritionInsteadBreastFeeding) {
        this.nutritionInsteadBreastFeeding = nutritionInsteadBreastFeeding;
    }

    public Timestamp getNutritionBeforeCutBreastFeeding() {
        return nutritionBeforeCutBreastFeeding;
    }

    public void setNutritionBeforeCutBreastFeeding(Timestamp nutritionBeforeCutBreastFeeding) {
        this.nutritionBeforeCutBreastFeeding = nutritionBeforeCutBreastFeeding;
    }

    public Timestamp getNutritionCutBreastFeeding() {
        return nutritionCutBreastFeeding;
    }

    public void setNutritionCutBreastFeeding(Timestamp nutritionCutBreastFeeding) {
        this.nutritionCutBreastFeeding = nutritionCutBreastFeeding;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Timestamp getOperatorTime() {
        return operatorTime;
    }

    public void setOperatorTime(Timestamp operatorTime) {
        this.operatorTime = operatorTime;
    }

    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    @Override
    public String toString() {
        return "BreedingRequest{" +
                "gmtCreate=" + gmtCreate +
                ", gmtModify=" + gmtModify +
                ", breedingTime=" + breedingTime +
                ", buildingAfterBreeding='" + buildingAfterBreeding + '\'' +
                ", ramSheepTrademark='" + ramSheepTrademark + '\'' +
                ", eweSheepTrademark='" + eweSheepTrademark + '\'' +
                ", manageFlag=" + manageFlag +
                ", manageAverageTime=" + manageAverageTime +
                ", nutritionBeforePregnancy=" + nutritionBeforePregnancy +
                ", isPregnancy='" + isPregnancy + '\'' +
                ", nutritionAfterPregnancy=" + nutritionAfterPregnancy +
                ", prenatalImmunityType='" + prenatalImmunityType + '\'' +
                ", prenatalImmunityTime='" + prenatalImmunityTime + '\'' +
                ", buildingToBeRelocated='" + buildingToBeRelocated + '\'' +
                ", nutritionBeforeLambing=" + nutritionBeforeLambing +
                ", lambingTime=" + lambingTime +
                ", lambingNumber=" + lambingNumber +
                ", nutritionBreastFeeding=" + nutritionBreastFeeding +
                ", nutritionInsteadBreastFeeding=" + nutritionInsteadBreastFeeding +
                ", nutritionBeforeCutBreastFeeding=" + nutritionBeforeCutBreastFeeding +
                ", nutritionCutBreastFeeding=" + nutritionCutBreastFeeding +
                ", remark='" + remark + '\'' +
                ", factoryNumber=" + factoryNumber +
                ", factoryName='" + factoryName + '\'' +
                ", operatorTime=" + operatorTime +
                ", operatorId=" + operatorId +
                ", operatorName='" + operatorName + '\'' +
                '}';
    }
}
