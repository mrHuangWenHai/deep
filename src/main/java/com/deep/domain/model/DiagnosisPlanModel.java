package com.deep.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * author: Created  By  Caojiawei
 * date: 2018/4/12  20:06
 */
public class DiagnosisPlanModel {

    int id;
    @NotBlank
    private String diagnosisResult;
    @NotBlank
    private String diagnosisMethod;
    @NotBlank
    private String dose;
    private String gmtCreate;
    private String gmtModified;
    @NotBlank
    private String treatEffect;
    @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss")
    private String gmtSup;
    @Min(0)
    private Integer factoryNum;
    @NotBlank
    private String factoryName;
    @Min(0)
    private int operatorId ;
    @NotBlank
    private String operatorName;
    private int professorId ;
    private int supervisorId ;
    private String remark;
    private int ispassCheck ;
    private String upassReason;
    private int ispassSup ;
    @NotBlank
    @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss")
    private String diagnosisTime;
    @Min(0)
    private int buildingNum = -1;
    @NotBlank
    @Size(min = 15, max = 15, message = "eartag need size:15 ")
    @Pattern(regexp = "^[0-9]+$", message = "商标耳牌由数字组成15位")
    private String earTag;

    private String supervisorName;

    private String professorName;

    public String getSupervisorName() {
        return supervisorName;
    }

    public void setSupervisorName(String supervisorName) {
        this.supervisorName = supervisorName;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getDiagnosisResult() {
    return diagnosisResult;
  }

  public void setDiagnosisResult(String diagnosisResult) {
    this.diagnosisResult = diagnosisResult;
  }

  public String getDiagnosisMethod() {
    return diagnosisMethod;
  }

  public void setDiagnosisMethod(String diagnosisMethod) {
    this.diagnosisMethod = diagnosisMethod;
  }

  public String getDose() {
    return dose;
  }

  public void setDose(String dose) {
    this.dose = dose;
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

  public String getTreatEffect() {
    return treatEffect;
  }

  public void setTreatEffect(String treatEffect) {
    this.treatEffect = treatEffect;
  }

  public String getGmtSup() {
    return gmtSup;
  }

  public void setGmtSup(String gmtSup) {
    this.gmtSup = gmtSup;
  }

  public Integer getFactoryNum() {
    return factoryNum;
  }

  public void setFactoryNum(Integer factoryNum) {
    this.factoryNum = factoryNum;
  }

  public String getFactoryName() {
    return factoryName;
  }

  public void setFactoryName(String factoryName) {
    this.factoryName = factoryName;
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

  public int getProfessorId() {
    return professorId;
  }

  public void setProfessorId(int professorId) {
    this.professorId = professorId;
  }

  public int getSupervisorId() {
    return supervisorId;
  }

  public void setSupervisorId(int supervisorId) {
    this.supervisorId = supervisorId;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public int getIspassCheck() {
    return ispassCheck;
  }

  public void setIspassCheck(int ispassCheck) {
    this.ispassCheck = ispassCheck;
  }

  public String getUpassReason() {
    return upassReason;
  }

  public void setUpassReason(String upassReason) {
    this.upassReason = upassReason;
  }

  public int getIspassSup() {
    return ispassSup;
  }

  public void setIspassSup(int ispassSup) {
    this.ispassSup = ispassSup;
  }

  public String getDiagnosisTime() {
    return diagnosisTime;
  }

  public void setDiagnosisTime(String diagnosisTime) {
    this.diagnosisTime = diagnosisTime;
  }

  public int getBuildingNum() {
    return buildingNum;
  }

  public void setBuildingNum(int buildingNum) {
    this.buildingNum = buildingNum;
  }

  public String getEarTag() {
    return earTag;
  }

  public void setEarTag(String earTag) {
    this.earTag = earTag;
  }

  @Override
    public String toString() {
        return "DiagnosisPlanModel{" +
            "id=" + id +
            ", diagnosisResult='" + diagnosisResult + '\'' +
            ", diagnosisMethod='" + diagnosisMethod + '\'' +
            ", dose='" + dose + '\'' +
            ", gmtCreate='" + gmtCreate + '\'' +
            ", gmtModified='" + gmtModified + '\'' +
            ", treatEffect='" + treatEffect + '\'' +
            ", gmtSup='" + gmtSup + '\'' +
            ", factoryNum=" + factoryNum +
            ", factoryName='" + factoryName + '\'' +
            ", operatorId=" + operatorId +
            ", operatorName='" + operatorName + '\'' +
            ", professorId=" + professorId +
            ", supervisorId=" + supervisorId +
            ", remark='" + remark + '\'' +
            ", ispassCheck=" + ispassCheck +
            ", upassReason='" + upassReason + '\'' +
            ", ispassSup=" + ispassSup +
            ", diagnosisTime='" + diagnosisTime + '\'' +
            ", buildingNum=" + buildingNum +
            ", earTag='" + earTag + '\'' +
            '}';
    }
}
