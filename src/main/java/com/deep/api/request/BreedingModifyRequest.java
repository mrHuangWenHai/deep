package com.deep.api.request;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class BreedingModifyRequest {
    // 记录修改时间，前端不传
    private Timestamp gmtModify;
    @NotNull(message = "配种时间不能为空")
    private String breedingTime;
    private String buildingAfterBreeding;
    @NotNull(message = "母羊商标耳牌不能为空")
    private String ramSheepTrademark;
    @NotNull(message = "公羊商标耳牌不能为空")
    private String eweSheepTrademark;
    private Byte manageFlag;
    private String manageAverageTime;
    private String nutritionBeforePregnancy;
    private String isPregnancy;
    private String nutritionAfterPregnancy;
    private String prenatalImmunityType;
    private String prenatalImmunityTime;
    private String buildingToBeRelocated;
    private String  nutritionBeforeLambing;
    private String lambingTime;
    private Integer lambingNumber;
    private String nutritionBreastFeeding;
    private String nutritionInsteadBreastFeeding;
    private String nutritionBeforeCutBreastFeeding;
    private String nutritionCutBreastFeeding;

    private String remark;

    private Timestamp operatorTime;
    private Integer operatorId;
    private String operatorName;

    public Timestamp getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Timestamp gmtModify) {
        this.gmtModify = gmtModify;
    }

    public String getBreedingTime() {
        return breedingTime;
    }

    public void setBreedingTime(String breedingTime) {
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

    public String getManageAverageTime() {
        return manageAverageTime;
    }

    public void setManageAverageTime(String manageAverageTime) {
        this.manageAverageTime = manageAverageTime;
    }

    public String getNutritionBeforePregnancy() {
        return nutritionBeforePregnancy;
    }

    public void setNutritionBeforePregnancy(String nutritionBeforePregnancy) {
        this.nutritionBeforePregnancy = nutritionBeforePregnancy;
    }

    public String getIsPregnancy() {
        return isPregnancy;
    }

    public void setIsPregnancy(String isPregnancy) {
        this.isPregnancy = isPregnancy;
    }

    public String getNutritionAfterPregnancy() {
        return nutritionAfterPregnancy;
    }

    public void setNutritionAfterPregnancy(String nutritionAfterPregnancy) {
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

    public String getNutritionBeforeLambing() {
        return nutritionBeforeLambing;
    }

    public void setNutritionBeforeLambing(String nutritionBeforeLambing) {
        this.nutritionBeforeLambing = nutritionBeforeLambing;
    }

    public String getLambingTime() {
        return lambingTime;
    }

    public void setLambingTime(String lambingTime) {
        this.lambingTime = lambingTime;
    }

    public Integer getLambingNumber() {
        return lambingNumber;
    }

    public void setLambingNumber(Integer lambingNumber) {
        this.lambingNumber = lambingNumber;
    }

    public String getNutritionBreastFeeding() {
        return nutritionBreastFeeding;
    }

    public void setNutritionBreastFeeding(String nutritionBreastFeeding) {
        this.nutritionBreastFeeding = nutritionBreastFeeding;
    }

    public String getNutritionInsteadBreastFeeding() {
        return nutritionInsteadBreastFeeding;
    }

    public void setNutritionInsteadBreastFeeding(String nutritionInsteadBreastFeeding) {
        this.nutritionInsteadBreastFeeding = nutritionInsteadBreastFeeding;
    }

    public String getNutritionBeforeCutBreastFeeding() {
        return nutritionBeforeCutBreastFeeding;
    }

    public void setNutritionBeforeCutBreastFeeding(String nutritionBeforeCutBreastFeeding) {
        this.nutritionBeforeCutBreastFeeding = nutritionBeforeCutBreastFeeding;
    }

    public String getNutritionCutBreastFeeding() {
        return nutritionCutBreastFeeding;
    }

    public void setNutritionCutBreastFeeding(String nutritionCutBreastFeeding) {
        this.nutritionCutBreastFeeding = nutritionCutBreastFeeding;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
        return "BreedingModifyRequest{" +
                "gmtModify=" + gmtModify +
                ", breedingTime='" + breedingTime + '\'' +
                ", buildingAfterBreeding='" + buildingAfterBreeding + '\'' +
                ", ramSheepTrademark='" + ramSheepTrademark + '\'' +
                ", eweSheepTrademark='" + eweSheepTrademark + '\'' +
                ", manageFlag=" + manageFlag +
                ", manageAverageTime='" + manageAverageTime + '\'' +
                ", nutritionBeforePregnancy='" + nutritionBeforePregnancy + '\'' +
                ", isPregnancy='" + isPregnancy + '\'' +
                ", nutritionAfterPregnancy='" + nutritionAfterPregnancy + '\'' +
                ", prenatalImmunityType='" + prenatalImmunityType + '\'' +
                ", prenatalImmunityTime='" + prenatalImmunityTime + '\'' +
                ", buildingToBeRelocated='" + buildingToBeRelocated + '\'' +
                ", nutritionBeforeLambing='" + nutritionBeforeLambing + '\'' +
                ", lambingTime='" + lambingTime + '\'' +
                ", lambingNumber=" + lambingNumber +
                ", nutritionBreastFeeding='" + nutritionBreastFeeding + '\'' +
                ", nutritionInsteadBreastFeeding='" + nutritionInsteadBreastFeeding + '\'' +
                ", nutritionBeforeCutBreastFeeding='" + nutritionBeforeCutBreastFeeding + '\'' +
                ", nutritionCutBreastFeeding='" + nutritionCutBreastFeeding + '\'' +
                ", remark='" + remark + '\'' +
                ", operatorTime=" + operatorTime +
                ", operatorId=" + operatorId +
                ", operatorName='" + operatorName + '\'' +
                '}';
    }
}
