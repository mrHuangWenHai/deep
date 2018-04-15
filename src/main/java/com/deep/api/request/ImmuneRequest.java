package com.deep.api.request;

import com.deep.domain.model.ImmunePlanModel;

import java.math.BigInteger;

/**
 * create by zhongrui on 18-4-13.
 */
public class ImmuneRequest {

    private BigInteger factoryNum;
    private String crowdNum;
    private String immuneEartag;
    private String immuneTime;
    private String immuneKind;
    private String immuneWay;
    private String immuneQuality;
    private String immuneDuring;
    private String operator;
    private String professor;
    private String supervisor;
    private String remark;
    private String isPass;
    private String unpassReason;
    private String isPass1;
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

    public String getImmuneQuality() {
        return immuneQuality;
    }

    public void setImmuneQuality(String immuneQuality) {
        this.immuneQuality = immuneQuality;
    }

    public String getImmuneDuring() {
        return immuneDuring;
    }

    public void setImmuneDuring(String immuneDuring) {
        this.immuneDuring = immuneDuring;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
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

    public String getIsPass() {
        return isPass;
    }

    public void setIsPass(String isPass) {
        this.isPass = isPass;
    }

    public String getUnpassReason() {
        return unpassReason;
    }

    public void setUnpassReason(String unpassReason) {
        this.unpassReason = unpassReason;
    }

    public String getIsPass1() {
        return isPass1;
    }

    public void setIsPass1(String isPass1) {
        this.isPass1 = isPass1;
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
}
