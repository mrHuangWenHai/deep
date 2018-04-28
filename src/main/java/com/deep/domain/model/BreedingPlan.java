package com.deep.domain.model;

import java.io.Serializable;
import java.util.Date;

public class BreedingPlan implements Serializable {
    private Integer id;

    private Date gmtCreate;

    private Date gmtModified;

    private Date gmtSupervised;

    private Long factoryNum;

    private String building;

    private String mEtI;

    private String mEtB;

    private String fEtI;

    private String fEtB;

    private Date breedingT;

    private Date gestationT;

    private Date prenatalIT;

    private Date cubT;

    private Integer quantity;

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

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building == null ? null : building.trim();
    }

    public String getmEtI() {
        return mEtI;
    }

    public void setmEtI(String mEtI) {
        this.mEtI = mEtI == null ? null : mEtI.trim();
    }

    public String getmEtB() {
        return mEtB;
    }

    public void setmEtB(String mEtB) {
        this.mEtB = mEtB == null ? null : mEtB.trim();
    }

    public String getfEtI() {
        return fEtI;
    }

    public void setfEtI(String fEtI) {
        this.fEtI = fEtI == null ? null : fEtI.trim();
    }

    public String getfEtB() {
        return fEtB;
    }

    public void setfEtB(String fEtB) {
        this.fEtB = fEtB == null ? null : fEtB.trim();
    }

    public Date getBreedingT() {
        return breedingT;
    }

    public void setBreedingT(Date breedingT) {
        this.breedingT = breedingT;
    }

    public Date getGestationT() {
        return gestationT;
    }

    public void setGestationT(Date gestationT) {
        this.gestationT = gestationT;
    }

    public Date getPrenatalIT() {
        return prenatalIT;
    }

    public void setPrenatalIT(Date prenatalIT) {
        this.prenatalIT = prenatalIT;
    }

    public Date getCubT() {
        return cubT;
    }

    public void setCubT(Date cubT) {
        this.cubT = cubT;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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