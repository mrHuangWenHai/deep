package com.deep.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

public class NutritionPlan implements Serializable {
    private Integer id;

    private Date gmtCreate;

    private Date gmtModified;

    private Date gmtSupervised;

    private Long factoryNum;

    private String factoryName;

    private String building;

    @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss")
    private Date nutritionT;

    private Long quantity;

    private String average;

    private String period;

    private String water;

    private String operatorName;

    private String professorName;

    private String supervisorName;

    private Long operatorId;

    private Long professorId;

    private Long supervisorId;

    private String remark;

    private Byte ispassCheck;

    private String upassReason;

    private Byte ispassSup;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Date getGmtSupervised() {
        return gmtSupervised;
    }

    public void setGmtSupervised(Date gmtSupervised) {
        this.gmtSupervised = gmtSupervised;
    }

    public Long getFactoryNum() {
        return factoryNum;
    }

    public void setFactoryNum(Long factoryNum) {
        this.factoryNum = factoryNum;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building == null ? null : building.trim();
    }

    public Date getNutritionT() {
        return nutritionT;
    }

    public void setNutritionT(Date nutritionT) {
        this.nutritionT = nutritionT;
    }

    /*public String getNutritionT() {
        return nutritionT;
    }

    public void setNutritionT(String nutritionT) {
        this.nutritionT = nutritionT == null ? null : nutritionT.trim();
    }*/

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public Long getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Long professorId) {
        this.professorId = professorId;
    }

    public Long getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(Long supervisorId) {
        this.supervisorId = supervisorId;
    }

    public Byte getIspassCheck() {
        return ispassCheck;
    }

    public void setIspassCheck(Byte ispassCheck) {
        this.ispassCheck = ispassCheck;
    }

    public Byte getIspassSup() {
        return ispassSup;
    }

    public void setIspassSup(Byte ispassSup) {
        this.ispassSup = ispassSup;
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
        this.average = average == null ? null : average.trim();
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period == null ? null : period.trim();
    }

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water == null ? null : water.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getUpassReason() {
        return upassReason;
    }

    public void setUpassReason(String upassReason) {
        this.upassReason = upassReason == null ? null : upassReason.trim();
    }
}