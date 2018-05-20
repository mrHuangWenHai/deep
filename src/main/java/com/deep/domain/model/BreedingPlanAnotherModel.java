package com.deep.domain.model;

import java.sql.Timestamp;

public class BreedingPlanAnotherModel {
    private Integer id;
    private Timestamp gmtCreate;
    private Timestamp gmtModify;

    private Byte stageFlag;

    // 第一阶段
    private String buildingAfterBreeding;
    private String buildingOld;
    private String ramSheepTrademark;
    private String eweSheepTrademark;
    private Timestamp breedingTime;
    private Timestamp pregnancyTime;
    private Timestamp operatorTimeFirst;
    private Integer operatorIdFirst;
    private String operatorNameFirst;
    private Byte isPassSupFirst;
    private Timestamp supervisorTimeFirst;
    private Integer supervisorIdFirst;
    private String supervisorNameFirst;
    private Byte isPassCheckFirst;
    private Timestamp professorTimeFirst;
    private Integer professorIdFirst;
    private String professorNameFirst;
    private String remarkFirst;

    // 第二阶段
    private String buildingToBeRelocated;
    private Timestamp lambingTime;
    private Integer lambingNumber;
    private Timestamp operatorTimeSecond;
    private Integer operatorIdSecond;
    private String operatorNameSecond;
    private Byte isPassSupSecond;
    private Timestamp supervisorTimeSecond;
    private Integer supervisorIdSecond;
    private String supervisorNameSecond;
    private Byte isPassCheckSecond;
    private Timestamp professorTimeSecond;
    private Integer professorIdSecond;
    private String professorNameSecond;
    private String remarkSecond;
    private String prenatalImmunityType;
    private String prenatalImmunityTime;

    // 羊场信息
    private Integer factoryNumber;
    private String factoryName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Timestamp getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Timestamp gmtModify) {
        this.gmtModify = gmtModify;
    }

    public Byte getStageFlag() {
        return stageFlag;
    }

    public void setStageFlag(Byte stageFlag) {
        this.stageFlag = stageFlag;
    }

    public String getBuildingAfterBreeding() {
        return buildingAfterBreeding;
    }

    public void setBuildingAfterBreeding(String buildingAfterBreeding) {
        this.buildingAfterBreeding = buildingAfterBreeding;
    }

    public String getBuildingOld() {
        return buildingOld;
    }

    public void setBuildingOld(String buildingOld) {
        this.buildingOld = buildingOld;
    }

    public String getRamSheepTrademark() {
        return ramSheepTrademark;
    }

    public void setRamSheepTrademark(String ramSheepTrademark) {
        this.ramSheepTrademark = ramSheepTrademark;
    }

    public String getEweSheepTrademark() {
        return eweSheepTrademark;
    }

    public void setEweSheepTrademark(String eweSheepTrademark) {
        this.eweSheepTrademark = eweSheepTrademark;
    }

    public Timestamp getBreedingTime() {
        return breedingTime;
    }

    public void setBreedingTime(Timestamp breedingTime) {
        this.breedingTime = breedingTime;
    }

    public Timestamp getPregnancyTime() {
        return pregnancyTime;
    }

    public void setPregnancyTime(Timestamp pregnancyTime) {
        this.pregnancyTime = pregnancyTime;
    }

    public Timestamp getOperatorTimeFirst() {
        return operatorTimeFirst;
    }

    public void setOperatorTimeFirst(Timestamp operatorTimeFirst) {
        this.operatorTimeFirst = operatorTimeFirst;
    }

    public Integer getOperatorIdFirst() {
        return operatorIdFirst;
    }

    public void setOperatorIdFirst(Integer operatorIdFirst) {
        this.operatorIdFirst = operatorIdFirst;
    }

    public String getOperatorNameFirst() {
        return operatorNameFirst;
    }

    public void setOperatorNameFirst(String operatorNameFirst) {
        this.operatorNameFirst = operatorNameFirst;
    }

    public Byte getIsPassSupFirst() {
        return isPassSupFirst;
    }

    public void setIsPassSupFirst(Byte isPassSupFirst) {
        this.isPassSupFirst = isPassSupFirst;
    }

    public Timestamp getSupervisorTimeFirst() {
        return supervisorTimeFirst;
    }

    public void setSupervisorTimeFirst(Timestamp supervisorTimeFirst) {
        this.supervisorTimeFirst = supervisorTimeFirst;
    }

    public Integer getSupervisorIdFirst() {
        return supervisorIdFirst;
    }

    public void setSupervisorIdFirst(Integer supervisorIdFirst) {
        this.supervisorIdFirst = supervisorIdFirst;
    }

    public String getSupervisorNameFirst() {
        return supervisorNameFirst;
    }

    public void setSupervisorNameFirst(String supervisorNameFirst) {
        this.supervisorNameFirst = supervisorNameFirst;
    }

    public Byte getIsPassCheckFirst() {
        return isPassCheckFirst;
    }

    public void setIsPassCheckFirst(Byte isPassCheckFirst) {
        this.isPassCheckFirst = isPassCheckFirst;
    }

    public Timestamp getProfessorTimeFirst() {
        return professorTimeFirst;
    }

    public void setProfessorTimeFirst(Timestamp professorTimeFirst) {
        this.professorTimeFirst = professorTimeFirst;
    }

    public Integer getProfessorIdFirst() {
        return professorIdFirst;
    }

    public void setProfessorIdFirst(Integer professorIdFirst) {
        this.professorIdFirst = professorIdFirst;
    }

    public String getProfessorNameFirst() {
        return professorNameFirst;
    }

    public void setProfessorNameFirst(String professorNameFirst) {
        this.professorNameFirst = professorNameFirst;
    }

    public String getRemarkFirst() {
        return remarkFirst;
    }

    public void setRemarkFirst(String remarkFirst) {
        this.remarkFirst = remarkFirst;
    }

    public String getBuildingToBeRelocated() {
        return buildingToBeRelocated;
    }

    public void setBuildingToBeRelocated(String buildingToBeRelocated) {
        this.buildingToBeRelocated = buildingToBeRelocated;
    }

    public Timestamp getLambingTime() {
        return lambingTime;
    }

    public void setLambingTime(Timestamp lambingTime) {
        this.lambingTime = lambingTime;
    }

    public Integer getLambingNumber() {
        return lambingNumber;
    }

    public void setLambingNumber(Integer lambingNumber) {
        this.lambingNumber = lambingNumber;
    }

    public Timestamp getOperatorTimeSecond() {
        return operatorTimeSecond;
    }

    public void setOperatorTimeSecond(Timestamp operatorTimeSecond) {
        this.operatorTimeSecond = operatorTimeSecond;
    }

    public Integer getOperatorIdSecond() {
        return operatorIdSecond;
    }

    public void setOperatorIdSecond(Integer operatorIdSecond) {
        this.operatorIdSecond = operatorIdSecond;
    }

    public String getOperatorNameSecond() {
        return operatorNameSecond;
    }

    public void setOperatorNameSecond(String operatorNameSecond) {
        this.operatorNameSecond = operatorNameSecond;
    }

    public Byte getIsPassSupSecond() {
        return isPassSupSecond;
    }

    public void setIsPassSupSecond(Byte isPassSupSecond) {
        this.isPassSupSecond = isPassSupSecond;
    }

    public Timestamp getSupervisorTimeSecond() {
        return supervisorTimeSecond;
    }

    public void setSupervisorTimeSecond(Timestamp supervisorTimeSecond) {
        this.supervisorTimeSecond = supervisorTimeSecond;
    }

    public Integer getSupervisorIdSecond() {
        return supervisorIdSecond;
    }

    public void setSupervisorIdSecond(Integer supervisorIdSecond) {
        this.supervisorIdSecond = supervisorIdSecond;
    }

    public String getSupervisorNameSecond() {
        return supervisorNameSecond;
    }

    public void setSupervisorNameSecond(String supervisorNameSecond) {
        this.supervisorNameSecond = supervisorNameSecond;
    }

    public Byte getIsPassCheckSecond() {
        return isPassCheckSecond;
    }

    public void setIsPassCheckSecond(Byte isPassCheckSecond) {
        this.isPassCheckSecond = isPassCheckSecond;
    }

    public Timestamp getProfessorTimeSecond() {
        return professorTimeSecond;
    }

    public void setProfessorTimeSecond(Timestamp professorTimeSecond) {
        this.professorTimeSecond = professorTimeSecond;
    }

    public Integer getProfessorIdSecond() {
        return professorIdSecond;
    }

    public void setProfessorIdSecond(Integer professorIdSecond) {
        this.professorIdSecond = professorIdSecond;
    }

    public String getProfessorNameSecond() {
        return professorNameSecond;
    }

    public void setProfessorNameSecond(String professorNameSecond) {
        this.professorNameSecond = professorNameSecond;
    }

    public String getRemarkSecond() {
        return remarkSecond;
    }

    public void setRemarkSecond(String remarkSecond) {
        this.remarkSecond = remarkSecond;
    }

    public String getPrenatalImmunityType() {
        return prenatalImmunityType;
    }

    public void setPrenatalImmunityType(String prenatalImmunityType) {
        this.prenatalImmunityType = prenatalImmunityType;
    }

    public String getPrenatalImmunityTime() {
        return prenatalImmunityTime;
    }

    public void setPrenatalImmunityTime(String prenatalImmunityTime) {
        this.prenatalImmunityTime = prenatalImmunityTime;
    }

    public Integer getFactoryNumber() {
        return factoryNumber;
    }

    public void setFactoryNumber(Integer factoryNumber) {
        this.factoryNumber = factoryNumber;
    }

    public String getFactoryName() {
        return factoryName;
    }

    public void setFactoryName(String factoryName) {
        this.factoryName = factoryName;
    }
}
