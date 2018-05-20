package com.deep.api.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * 配种产子档案第二阶段 操作员提交
 */
public class BreedingOperatorSecond {
    private Long id;
    private Timestamp gmtModify;
    // 第二阶段
    @NotNull(message = "产子后待搬栏栋不能为空")
    private String buildingToBeRelocated;

    @NotNull(message = "产子时间不能为空")
    private Timestamp lambingTime;

    @NotNull(message = "产子数量不能为空")
    @Min(value = 0)
    private Integer lambingNumber;

    @NotNull(message = "操作员操作时间不能为空")
    private Timestamp operatorTimeSecond;

    @NotNull(message = "操作员ID不能为空")
    private Integer operatorIdSecond;

    @NotNull(message = "操作员姓名不能为空")
    private String operatorNameSecond;

    @NotNull(message = "第二阶段操作备注不能为空")
    private String remarkSecond;

    @NotNull(message = "产前免疫种类不能为空")
    private String prenatalImmunityType;

    @NotNull(message = "产前免疫时间不能为空")
    private String prenatalImmunityTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Timestamp gmtModify) {
        this.gmtModify = gmtModify;
    }

    public String getBuildingToBeRelocated() {
        return buildingToBeRelocated;
    }

    public void setBuildingToBeRelocated(String buildingToBeRelocated) {
        this.buildingToBeRelocated = buildingToBeRelocated;
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

    public Timestamp getOperatorTimeSecond() {
        return operatorTimeSecond;
    }

    public void setOperatorTimeSecond(Timestamp operatorTimeSecond) {
        this.operatorTimeSecond = operatorTimeSecond;
    }

    public Integer getOperatorIdSecond() {
        return operatorIdSecond;
    }

    public void setOperatorIdSecond(Integer operatorIdSecond) {
        this.operatorIdSecond = operatorIdSecond;
    }

    public String getOperatorNameSecond() {
        return operatorNameSecond;
    }

    public void setOperatorNameSecond(String operatorNameSecond) {
        this.operatorNameSecond = operatorNameSecond;
    }

    public String getRemarkSecond() {
        return remarkSecond;
    }

    public void setRemarkSecond(String remarkSecond) {
        this.remarkSecond = remarkSecond;
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
}
