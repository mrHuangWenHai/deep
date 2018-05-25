package com.deep.domain.model;

import javax.validation.constraints.*;
import java.math.BigInteger;

public class ImmunePlanModel {

    private Long id;      //id
    @NotNull
    @Min(0)
    private BigInteger factoryNum;  //工厂编号
    private String factoryName;
    @NotBlank
    private String crowdNum;     //羊群号
    private String immuneEartag;    //耳牌附件名
    private String immuneTime;     //接种时间
    @NotBlank
    private String immuneKind;     //免疫种类
    @NotBlank
    private String immuneWay;       //免疫方法
    @NotBlank
    private String dose;    //免疫剂量
    @NotBlank
    private String immuneDuring;    //免疫期
    @Min(0)

    private Integer operatorId;

    @NotBlank
    private String operatorName;     //操作员
    private String professor;    //技术审核
    private String supervisor;    //监督员
    private String name;
    private String remark;      //备注
    private String ispassCheck;      //是否通过审核 默认 未审核 0
    private String unpassReason;   //未通过审核原因
    private String ispassSup;      //是否通过监督 默认 未监督 0
    private String gmtCreate;    //创建时间
    private String gmtModified;  //修改时间
    private String gmtProfessor;    //技术审核时间 可空
    private String gmtSupervise;    //监督审核时间 可空
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigInteger getFactoryNum() {
        return factoryNum;
    }

    public void setFactoryNum(BigInteger factoryNum) {
        this.factoryNum = factoryNum;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }

    public String getCrowdNum() {
        return crowdNum;
    }

    public void setCrowdNum(String crowdNum) {
        this.crowdNum = crowdNum;
    }

    public String getImmuneEartag() {
        return immuneEartag;
    }

    public void setImmuneEartag(String immuneEartag) {
        this.immuneEartag = immuneEartag;
    }

    public String getImmuneTime() {
        return immuneTime;
    }

    public void setImmuneTime(String immuneTime) {
        this.immuneTime = immuneTime;
    }

    public String getImmuneKind() {
        return immuneKind;
    }

    public void setImmuneKind(String immuneKind) {
        this.immuneKind = immuneKind;
    }

    public String getImmuneWay() {
        return immuneWay;
    }

    public void setImmuneWay(String immuneWay) {
        this.immuneWay = immuneWay;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getImmuneDuring() {
        return immuneDuring;
    }

    public void setImmuneDuring(String immuneDuring) {
        this.immuneDuring = immuneDuring;
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
        return "ImmunePlanModel{" +
            "id=" + id +
            ", factoryNum=" + factoryNum +
            ", crowdNum='" + crowdNum + '\'' +
            ", immuneEartag='" + immuneEartag + '\'' +
            ", immuneTime='" + immuneTime + '\'' +
            ", immuneKind='" + immuneKind + '\'' +
            ", immuneWay='" + immuneWay + '\'' +
            ", dose='" + dose + '\'' +
            ", immuneDuring='" + immuneDuring + '\'' +
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
