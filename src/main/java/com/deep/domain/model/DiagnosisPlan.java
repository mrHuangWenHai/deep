package com.deep.domain.model;

import java.io.Serializable;
import java.util.Date;

public class DiagnosisPlan implements Serializable {
    private Integer id;

    private Date gmtCreate;

    private Date gmtModified;

    private Date gmtSupervised;

    private Long factoryNum;

    private Date diagnosisT;

    private String building;

    private String etB;

    private String operator;

    private String professor;

    private String supervisor;

    private String remark;

    private Byte isPass;

    private String upassReason;

    private Byte isPass1;

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

    public Date getDiagnosisT() {
        return diagnosisT;
    }

    public void setDiagnosisT(Date diagnosisT) {
        this.diagnosisT = diagnosisT;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building == null ? null : building.trim();
    }

    public String getEtB() {
        return etB;
    }

    public void setEtB(String etB) {
        this.etB = etB == null ? null : etB.trim();
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor == null ? null : professor.trim();
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor == null ? null : supervisor.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Byte getIsPass() {
        return isPass;
    }

    public void setIsPass(Byte isPass) {
        this.isPass = isPass;
    }

    public String getUpassReason() {
        return upassReason;
    }

    public void setUpassReason(String upassReason) {
        this.upassReason = upassReason == null ? null : upassReason.trim();
    }

    public Byte getIsPass1() {
        return isPass1;
    }

    public void setIsPass1(Byte isPass1) {
        this.isPass1 = isPass1;
    }
}