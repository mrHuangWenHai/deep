package com.deep.domain.model;


import javax.validation.constraints.*;
import java.math.BigInteger;

public class ImmunePlanModel {
    private Long id;      //id

    @NotNull
    @Min(0)
    private BigInteger factoryNum;  //工厂编号
    @NotBlank
    private String crowdNum;     //羊群号
    private String immuneEartag;    //耳牌附件名
    @NotBlank
    private String immuneTime;     //接种时间
    @NotBlank
    private String immuneKind;     //免疫种类
    @NotBlank
    private String immuneWay;       //免疫方法
    @NotBlank
    private String immuneQuality;    //免疫剂量
    @NotBlank
    private String immuneDuring;    //免疫期
    @NotBlank
    private String operator;     //操作员
    private String professor;    //技术审核
    private String supervisor;    //监督员
    @NotBlank
    private String remark;      //备注
    private String isPass;      //是否通过审核 默认 未审核 0
    private String unpassReason;   //未通过审核原因
    private String isPass1;      //是否通过监督 默认 未监督 0
    private String gmtCreate;    //创建时间
    private String gmtModified;  //修改时间
    private String gmtProfessor;    //技术审核时间 可空
    private String gmtSupervise;    //监督审核时间 可空

    //



    public ImmunePlanModel() {
    }

    public ImmunePlanModel(Long id, BigInteger factoryNum, String crowdNum, String immuneEartag, String immuneTime, String immuneKind, String immuneWay, String immuneQuality, String immuneDuring, String operator, String professor, String supervisor, String remark, String isPass, String unpassReason, String isPass1, String gmtCreate, String gmtModified, String gmtProfessor, String gmtSupervise) {
        this.id = id;
        this.factoryNum = factoryNum;
        this.crowdNum = crowdNum;
        this.immuneEartag = immuneEartag;
        this.immuneTime = immuneTime;
        this.immuneKind = immuneKind;
        this.immuneWay = immuneWay;
        this.immuneQuality = immuneQuality;
        this.immuneDuring = immuneDuring;
        this.operator = operator;
        this.professor = professor;
        this.supervisor = supervisor;
        this.remark = remark;
        this.isPass = isPass;
        this.unpassReason = unpassReason;
        this.isPass1 = isPass1;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.gmtProfessor = gmtProfessor;
        this.gmtSupervise = gmtSupervise;

    }

    public ImmunePlanModel( BigInteger factoryNum, String crowdNum, String immuneTime, String immuneKind, String immuneWay, String immuneQuality, String immuneDuring, String operator, String professor, String supervisor, String remark, String isPass, String unpassReason, String isPass1, String gmtCreate) {

        this.factoryNum = factoryNum;
        this.crowdNum = crowdNum;
        this.immuneTime = immuneTime;
        this.immuneKind = immuneKind;
        this.immuneWay = immuneWay;
        this.immuneQuality = immuneQuality;
        this.immuneDuring = immuneDuring;
        this.operator = operator;
        this.professor = professor;
        this.supervisor = supervisor;
        this.remark = remark;
        this.isPass = isPass;
        this.unpassReason = unpassReason;
        this.isPass1 = isPass1;
        this.gmtCreate = gmtCreate;

    }

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

}
