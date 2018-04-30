package com.deep.api.request;

import com.deep.domain.model.ImmunePlanModel;

import java.math.BigInteger;

/**
 * create by zhongrui on 18-4-13.
 */
public class ImmuneRequest {


    private Long id;

    private BigInteger factoryNum;
    private String factoryName;
    private String crowdNum;
    private String immuneEartag;
    private String immuneTime;
    private String immuneKind;
    private String immuneWay;
    private String dose;
    private String immuneDuring;
    private int operatorId;
    private String operatorName;
    private String professor;
    private String supervisor;
    private String remark;
    private String ispassCheck;
    private String unpassReason;
    private String ispassSup;
    private String gmtCreate;
    private String gmtModified;
    private String gmtProfessor;
    private String gmtSupervise;

    private String immuneTimeStart;
    private String immuneTimeEnd;
    private int page;
    private int size;

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

    public String getImmuneTimeStart() {
        return immuneTimeStart;
    }

    public void setImmuneTimeStart(String immuneTimeStart) {
        this.immuneTimeStart = immuneTimeStart;
    }

    public String getImmuneTimeEnd() {
        return immuneTimeEnd;
    }

    public void setImmuneTimeEnd(String immuneTimeEnd) {
        this.immuneTimeEnd = immuneTimeEnd;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ImmuneRequest{" +
            "id=" + id +
            ", factoryNum=" + factoryNum +
            ", factoryName='" + factoryName + '\'' +
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
            ", immuneTimeStart='" + immuneTimeStart + '\'' +
            ", immuneTimeEnd='" + immuneTimeEnd + '\'' +
            ", page=" + page +
            ", size=" + size +
            '}';
    }
}
