package com.deep.api.request;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

public class BreedingRequest {
    // 记录创建时间，前端不传
    private Timestamp gmtCreate;
    // 记录修改时间，前端不传
    private Timestamp gmtModify;

    // 配种时间
    @NotNull(message = "配种时间不能为空")
    private Timestamp breedingTime;

    // 配种后待移至栏栋，可以为空
    private String buildingAfterBreeding;

    // 母羊商标耳牌
    @NotNull(message = "母羊商标耳牌不能为空")
    private String ramSheepTrademark;

    // 公羊商标耳牌
    @NotNull(message = "公羊商标耳牌不能为空")
    private String eweSheepTrademark;

    // 管理批次（int类型）
    private Byte manageFlag;

    // 批次平均配种时间（TimeStamp格式，可以用其他模块的时间控件）
    private Timestamp manageAverageTime;

    // 获取执行妊娠前期营养标准
    private Timestamp nutritionBeforePregnancy;

    // 确定妊娠
    private String isPregnancy;

    // 获取执行妊娠后期营养标准
    private Timestamp nutritionAfterPregnancy;

    // 产前免疫种类
    private String prenatalImmunityType;

    // 产前免疫时间
    private String prenatalImmunityTime;

    // 移至待产栏栋
    private String buildingToBeRelocated;

    // 执行产前营养标准
    private Timestamp nutritionBeforeLambing;

    // 产羔时间
    private Timestamp lambingTime;

    // 产羔数量
    private Integer lambingNumber;

    // 执行哺乳期营养标准（产后一周）
    private Timestamp nutritionBreastFeeding;

    // 执行羔羊代乳料营养标准（羔羊一月龄）
    private Timestamp nutritionInsteadBreastFeeding;

    // 执行断奶前母羊营养标准
    private Timestamp nutritionBeforeCutBreastFeeding;

    // 执行羔羊断奶期营养标准
    private Timestamp nutritionCutBreastFeeding;

    // 备注
    private String remark;

    // 羊场编号，Userdetail接口返回的FactoryNumber字段
    private Integer factoryNumber;
    // 羊场名称，Userdetail接口返回的FactoryName字段
    private String factoryName;

    // 操作员提交记录时间，前端不传
    private Timestamp operatorTime;

    // 操作员ID，Userdetail接口返回的id字段
    private Integer operatorId;

    // 操作员姓名，Userdetail接口返回的UserRealname字段
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
