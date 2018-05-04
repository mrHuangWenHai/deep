package com.deep.domain.model;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigInteger;

public class DisinfectFilesModel {

    private Long id;      //id

    @NotBlank
    private String place;

    @Min(0)
    private BigInteger factoryNum;
    @NotBlank
    private String factoryName;
    private String disinfectEartag;
    @NotBlank
    private String disinfectTime;     //消毒时间
    @NotBlank
    private String disinfectName;     //消毒药品名称
    @NotBlank

    private String dose;    //用药剂量
    @NotBlank
    private String disinfectWay;     //消毒方法
    @Min(0)
    private Integer operatorId;

    @NotBlank
    private String operatorName;      //操作员(创建表操作人员)
    private String professor;       //技术审核(审核表人员 专家) 可空
    private String supervisor;       //监督员(监督操作员人员) 可空
    private String remark;        //备注
    private String ispassCheck;       //是否通过审核 可空 默认为 0
    private String unpassReason;   //未通过审核原因 可空 默认未 无
    private String ispassSup;       //是否通过审核 可空 默认为 0
    private String gmtCreate;   //创建时间
    private String gmtModified;     //修改时间
    private String gmtProfessor;     //审核时间 可空
    private String gmtSupervise;     //监督确认时间 可空


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisinfectEartag() {
        return disinfectEartag;
    }

    public void setDisinfectEartag(String disinfectEartag) {
        this.disinfectEartag = disinfectEartag;
    }

    public String getDisinfectTime() {
        return disinfectTime;
    }

    public void setDisinfectTime(String disinfectTime) {
        this.disinfectTime = disinfectTime;
    }

    public String getDisinfectName() {
        return disinfectName;
    }

    public void setDisinfectName(String disinfectName) {
        this.disinfectName = disinfectName;
    }

    public String getDisinfectWay() {
        return disinfectWay;
    }

    public void setDisinfectWay(String disinfectWay) {
        this.disinfectWay = disinfectWay;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUnpassReason() {
        return unpassReason;
    }

    public void setUnpassReason(String unpassReason) {
        this.unpassReason = unpassReason;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(String gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getGmtProfessor() {
        return gmtProfessor;
    }

    public void setGmtProfessor(String gmtProfessor) {
        this.gmtProfessor = gmtProfessor;
    }

    public String getGmtSupervise() {
        return gmtSupervise;
    }

    public void setGmtSupervise(String gmtSupervise) {
        this.gmtSupervise = gmtSupervise;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getIspassCheck() {
        return ispassCheck;
    }

    public void setIspassCheck(String ispassCheck) {
        this.ispassCheck = ispassCheck;
    }

    public String getIspassSup() {
        return ispassSup;
    }

    public void setIspassSup(String ispassSup) {
        this.ispassSup = ispassSup;
    }

    public BigInteger getFactoryNum() {
        return factoryNum;
    }

    public void setFactoryNum(BigInteger factoryNum) {
        this.factoryNum = factoryNum;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }


    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {

        this.operatorId = operatorId;
    }

    @Override
    public String toString() {
        return "DisinfectFilesModel{" +
            "id=" + id +
            ", factoryNum=" + factoryNum +
            ", factoryName='" + factoryName + '\'' +
            ", disinfectEartag='" + disinfectEartag + '\'' +
            ", place='" + place + '\'' +
            ", disinfectTime='" + disinfectTime + '\'' +
            ", disinfectName='" + disinfectName + '\'' +
            ", dose='" + dose + '\'' +
            ", disinfectWay='" + disinfectWay + '\'' +
            ", operatorId=" + operatorId +
            ", operatorName='" + operatorName + '\'' +
            ", professor='" + professor + '\'' +
            ", supervisor='" + supervisor + '\'' +
            ", remark='" + remark + '\'' +
            ", ispassCheck='" + ispassCheck + '\'' +
            ", unpassReason='" + unpassReason + '\'' +
            ", ispassSup='" + ispassSup + '\'' +
            ", gmtCreate='" + gmtCreate + '\'' +
            ", gmtModified='" + gmtModified + '\'' +
            ", gmtProfessor='" + gmtProfessor + '\'' +
            ", gmtSupervise='" + gmtSupervise + '\'' +
            '}';
    }
}
