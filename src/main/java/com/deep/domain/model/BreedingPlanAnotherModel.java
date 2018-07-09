package com.deep.domain.model;

import java.sql.Date;
import java.sql.Timestamp;

public class BreedingPlanAnotherModel {
    private Integer id;
    private Timestamp gmtCreate;
    private Timestamp gmtModify;

    private Date breedingTime;
    private String buildingAfterBreeding;
    private String ramSheepTrademark;
    private String eweSheepTrademark;
    private Byte manageFlag;
    private Date manageAverageTime;

    private Date nutritionBeforePregnancy;
    private String isPregnancy;
    private Date nutritionAfterPregnancy;

    private String prenatalImmunityType;
    private String prenatalImmunityTime;
    private String buildingToBeRelocated;

    private Date nutritionBeforeLambing;
    private Date lambingTime;
    private Integer lambingNumber;

    private Date nutritionBreastFeeding;
    private Date nutritionInsteadBreastFeeding;
    private Date nutritionBeforeCutBreastFeeding;
    private Date nutritionCutBreastFeeding;

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
    private String professorNotPassReason;
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

    public Date getBreedingTime() {
        return breedingTime;
    }

    public void setBreedingTime(Date breedingTime) {
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

    public Date getManageAverageTime() {
        return manageAverageTime;
    }

    public void setManageAverageTime(Date manageAverageTime) {
        this.manageAverageTime = manageAverageTime;
    }

    public Date getNutritionBeforePregnancy() {
        return nutritionBeforePregnancy;
    }

    public void setNutritionBeforePregnancy(Date nutritionBeforePregnancy) {
        this.nutritionBeforePregnancy = nutritionBeforePregnancy;
    }

    public String getIsPregnancy() {
        return isPregnancy;
    }

    public void setIsPregnancy(String isPregnancy) {
        this.isPregnancy = isPregnancy;
    }

    public Date getNutritionAfterPregnancy() {
        return nutritionAfterPregnancy;
    }

    public void setNutritionAfterPregnancy(Date nutritionAfterPregnancy) {
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

    public Date getNutritionBeforeLambing() {
        return nutritionBeforeLambing;
    }

    public void setNutritionBeforeLambing(Date nutritionBeforeLambing) {
        this.nutritionBeforeLambing = nutritionBeforeLambing;
    }

    public Date getLambingTime() {
        return lambingTime;
    }

    public void setLambingTime(Date lambingTime) {
        this.lambingTime = lambingTime;
    }

    public Integer getLambingNumber() {
        return lambingNumber;
    }

    public void setLambingNumber(Integer lambingNumber) {
        this.lambingNumber = lambingNumber;
    }

    public Date getNutritionBreastFeeding() {
        return nutritionBreastFeeding;
    }

    public void setNutritionBreastFeeding(Date nutritionBreastFeeding) {
        this.nutritionBreastFeeding = nutritionBreastFeeding;
    }

    public Date getNutritionInsteadBreastFeeding() {
        return nutritionInsteadBreastFeeding;
    }

    public void setNutritionInsteadBreastFeeding(Date nutritionInsteadBreastFeeding) {
        this.nutritionInsteadBreastFeeding = nutritionInsteadBreastFeeding;
    }

    public Date getNutritionBeforeCutBreastFeeding() {
        return nutritionBeforeCutBreastFeeding;
    }

    public void setNutritionBeforeCutBreastFeeding(Date nutritionBeforeCutBreastFeeding) {
        this.nutritionBeforeCutBreastFeeding = nutritionBeforeCutBreastFeeding;
    }

    public Date getNutritionCutBreastFeeding() {
        return nutritionCutBreastFeeding;
    }

    public void setNutritionCutBreastFeeding(Date nutritionCutBreastFeeding) {
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

    public String getProfessorNotPassReason() {
        return professorNotPassReason;
    }

    public void setProfessorNotPassReason(String professorNotPassReason) {
        this.professorNotPassReason = professorNotPassReason;
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
                ", professorNotPassReason='" + professorNotPassReason + '\'' +
                ", remark='" + remark + '\'' +
                ", factoryNumber=" + factoryNumber +
                ", factoryName='" + factoryName + '\'' +
                '}';
    }
}
