package com.deep.domain.model;

import java.io.Serializable;
import java.util.Date;

public class BreedingPlan implements Serializable {
    private Integer id;

    private Date gmtCreate;

    private Date gmtModified;

    private Date gmtSupervised;

    private Long factoryNum;

    private String factoryName;

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

    private String operatorName;

    private String professorName;

    private String supervisorName;

    private String remark;

    private Byte ispassCheck;

    private String upassReason;

    private Byte ispassSup;

    private Integer operatorId;

    private Integer professorId;

    private Integer supervisorId;

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

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName == null ? null : operatorName.trim();
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName == null ? null : professorName.trim();
    }

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName == null ? null : supervisorName.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Byte getIsPassCheck() {
        return ispassCheck;
    }

    public void setIsPassCheck(Byte ispassCheck) {
        this.ispassCheck = ispassCheck;
    }

    public Byte getIsPassSup() {
        return ispassSup;
    }

    public void setIsPassSup(Byte ispassSup) {
        this.ispassSup = ispassSup;
    }

    public String getUpassReason() {
        return upassReason;
    }

    public void setUpassReason(String upassReason) {
        this.upassReason = upassReason == null ? null : upassReason.trim();
    }


    public Integer getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Integer operatorId) {
        this.operatorId = operatorId;
    }

    public Integer getProfessorId() {
        return professorId;
    }

    public void setProfessorId(Integer professorId) {
        this.professorId = professorId;
    }

    public Integer getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(Integer supervisorId) {
        this.supervisorId = supervisorId;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }
}