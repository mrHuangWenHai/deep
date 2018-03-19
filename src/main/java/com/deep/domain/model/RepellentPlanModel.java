package com.deep.domain.model;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

public class RepellentPlanModel {
    //private BigInteger id;      //id
    private BigInteger factoryNum;   //工厂编号
    private String crowdNum;       //羊群号
    private String repellentEartag;    //耳牌附件号
    private String repellentTime;    //驱虫时间
    private String repellentName;   //药物名称
    private String repellentWay;     //给药方式
    private String repellentQuality;    //给药剂量
    private String operator;
    private String professor;
    private String supervisor;
    private String remark;
    private String isPass1;
    private String unpassReason1;
    private String isPass2;
    private String unpassReason2;
    private Timestamp gmtCreate;
    private Timestamp gmtModified;
    private Timestamp gmtProfessor;
    private Timestamp gmtSupervise;

    public RepellentPlanModel() {
    }

    public RepellentPlanModel(BigInteger factoryNum, String crowdNum, String repellentEartag, String repellentTime, String repellentName, String repellentWay, String repellentQuality, String operator, String remark, String isPass1, String isPass2, Timestamp gmtCreate) {
        this.factoryNum = factoryNum;
        this.crowdNum = crowdNum;
        this.repellentEartag = repellentEartag;
        this.repellentTime = repellentTime;
        this.repellentName = repellentName;
        this.repellentWay = repellentWay;
        this.repellentQuality = repellentQuality;
        this.operator = operator;
        this.remark = remark;
        this.isPass1 = isPass1;
        this.isPass2 =isPass2;
        this.gmtCreate = gmtCreate;
    }

    public RepellentPlanModel(BigInteger factoryNum, String crowdNum, String repellentEartag, String repellentTime, String repellentName, String repellentWay, String repellentQuality, String operator, String professor, String supervisor, String remark, String isPass1, String isPass2, Timestamp gmtCreate) {
        this.factoryNum = factoryNum;
        this.crowdNum = crowdNum;
        this.repellentEartag = repellentEartag;
        this.repellentTime = repellentTime;
        this.repellentName = repellentName;
        this.repellentWay = repellentWay;
        this.repellentQuality = repellentQuality;
        this.operator = operator;
        this.professor = professor;
        this.supervisor = supervisor;
        this.remark = remark;
        this.isPass1 = isPass1;
        this.isPass2 = isPass2;
        this.gmtCreate = gmtCreate;
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

    public String getIsPass1() {
        return isPass1;
    }

    public void setIsPass1(String isPass1) {
        this.isPass1 = isPass1;
    }

    public String getUnpassReason1() {
        return unpassReason1;
    }

    public void setUnpassReason1(String unpassReason1) {
        this.unpassReason1 = unpassReason1;
    }

    public String getIsPass2() {
        return isPass2;
    }

    public void setIsPass2(String isPass2) {
        this.isPass2 = isPass2;
    }

    public String getUnpassReason2() {
        return unpassReason2;
    }

    public void setUnpassReason2(String unpassReason2) {
        this.unpassReason2 = unpassReason2;
    }

    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Timestamp getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Timestamp gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Timestamp getGmtProfessor() {
        return gmtProfessor;
    }

    public void setGmtProfessor(Timestamp gmtProfessor) {
        this.gmtProfessor = gmtProfessor;
    }

    public Timestamp getGmtSupervise() {
        return gmtSupervise;
    }

    public void setGmtSupervise(Timestamp gmtSupervise) {
        this.gmtSupervise = gmtSupervise;
    }
}
