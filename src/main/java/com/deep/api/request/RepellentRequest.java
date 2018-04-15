package com.deep.api.request;

import com.deep.domain.model.RepellentPlanModel;

import java.math.BigInteger;

/**
 * create by zhongrui on 18-4-13.
 */
public class RepellentRequest {

    private Long id;
    private BigInteger factoryNum;
    private String crowdNum;
    private String repellentEartag;
    private String repellentTime;
    private String repellentName;
    private String repellentWay;
    private String repellentQuality;
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

    private String repellentTimeStart;
    private String repellentTimeEnd;
    private int page;
    private int size;


    public Long getId() {
        return id;
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

    public String getCrowdNum() {
        return crowdNum;
    }

    public void setCrowdNum(String crowdNum) {
        this.crowdNum = crowdNum;
    }

    public String getRepellentEartag() {
        return repellentEartag;
    }

    public void setRepellentEartag(String repellentEartag) {
        this.repellentEartag = repellentEartag;
    }

    public String getRepellentTime() {
        return repellentTime;
    }

    public void setRepellentTime(String repellentTime) {
        this.repellentTime = repellentTime;
    }

    public String getRepellentName() {
        return repellentName;
    }

    public void setRepellentName(String repellentName) {
        this.repellentName = repellentName;
    }

    public String getRepellentWay() {
        return repellentWay;
    }

    public void setRepellentWay(String repellentWay) {
        this.repellentWay = repellentWay;
    }

    public String getRepellentQuality() {
        return repellentQuality;
    }

    public void setRepellentQuality(String repellentQuality) {
        this.repellentQuality = repellentQuality;
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

    public String getRepellentTimeStart() {
        return repellentTimeStart;
    }

    public void setRepellentTimeStart(String repellentTimeStart) {
        this.repellentTimeStart = repellentTimeStart;
    }

    public String getRepellentTimeEnd() {
        return repellentTimeEnd;
    }

    public void setRepellentTimeEnd(String repellentTimeEnd) {
        this.repellentTimeEnd = repellentTimeEnd;
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
