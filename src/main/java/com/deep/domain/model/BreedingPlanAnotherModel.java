package com.deep.domain.model;

import java.sql.Timestamp;

public class BreedingPlanAnotherModel {
    private Integer id;
    private Timestamp gmtCreate;
    private Timestamp gmtModify;
    private Timestamp breedingTime;
    private String buildingAfterBreeding;
    private String ramSheepTrademark;
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
    private Timestamp operatorTime;
    private Integer operatorId;
    private String operatorName;
    private Byte ispassSup;
    private Timestamp supervisorTime;
    private Integer supervisorId;
    private String supervisorName;
    private Byte ispassCheck;
    private Timestamp professorTime;
    private Integer professorId;
    private String professorName;
    private String remark;

    // 羊场信息
    private Integer factoryNumber;
    private String factoryName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Byte getIspassSup() {
        return ispassSup;
    }

    public void setIspassSup(Byte ispassSup) {
        this.ispassSup = ispassSup;
    }

    public Timestamp getSupervisorTime() {
        return supervisorTime;
    }

    public void setSupervisorTime(Timestamp supervisorTime) {
        this.supervisorTime = supervisorTime;
    }

    public Integer getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(Integer supervisorId) {
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

    public Timestamp getProfessorTime() {
        return professorTime;
    }

    public void setProfessorTime(Timestamp professorTime) {
        this.professorTime = professorTime;
    }

    public Integer getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Integer professorId) {
        this.professorId = professorId;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
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

    @Override
    public String toString() {
        return "BreedingPlanAnotherModel{" +
                "id=" + id +
                ", gmtCreate=" + gmtCreate +
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
                ", operatorTime=" + operatorTime +
                ", operatorId=" + operatorId +
                ", operatorName='" + operatorName + '\'' +
                ", ispassSup=" + ispassSup +
                ", supervisorTime=" + supervisorTime +
                ", supervisorId=" + supervisorId +
                ", supervisorName='" + supervisorName + '\'' +
                ", ispassCheck=" + ispassCheck +
                ", professorTime=" + professorTime +
                ", professorId=" + professorId +
                ", professorName='" + professorName + '\'' +
                ", remark='" + remark + '\'' +
                ", factoryNumber=" + factoryNumber +
                ", factoryName='" + factoryName + '\'' +
                '}';
    }
}
