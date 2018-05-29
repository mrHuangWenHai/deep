package com.deep.api.request;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.List;

/**
 * Created by huangwenhai on 2018/4/30.
 */
public class DiagnosisRequest {
  int id;
  private String diagnosisResult;
  private String diagnosisMethod;
  private String dose;
  private String gmtCreate;
  private String gmtModified;
  @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss")
  private String gmtSup;
  private Long factoryNum;
  private String factoryName;
  private Integer operatorId;
  private String operatorName;
  private Integer professorId;
  private Integer supervisorId;
  private String remark;
  private Integer ispassCheck;
  private String upassReason;
  private Integer ispassSup;

  private String supervisorName;

  private String professorName;

  @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss")
  private String diagnosisTime;
  @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss")
  private String startTime;
  @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss")
  private String endTime;
  private Integer buildingNum;
  private String earTag;
  private Integer page = 0;
  private Integer size = 10;
  private List<Long> factoryList;

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

  public String getGmtSup() {
    return gmtSup;
  }

  public void setGmtSup(String gmtSup) {
    this.gmtSup = gmtSup;
  }

  public Long getFactoryNum() {
    return factoryNum;
  }

  public void setFactoryNum(Long factoryNum) {
    this.factoryNum = factoryNum;
  }

  public String getFactoryName() {
    return factoryName;
  }

  public void setFactoryName(String factoryName) {
    this.factoryName = factoryName;
  }

  public Integer getOperatorId() {
    return operatorId;
  }

  public void setOperatorId(Integer operatorId) {
    this.operatorId = operatorId;
  }

  public String getOperatorName() {
    return operatorName;
  }

  public void setOperatorName(String operatorName) {
    this.operatorName = operatorName;
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

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public Integer getIspassCheck() {
    return ispassCheck;
  }

  public void setIspassCheck(Integer ispassCheck) {
    this.ispassCheck = ispassCheck;
  }

  public String getUpassReason() {
    return upassReason;
  }

  public void setUpassReason(String upassReason) {
    this.upassReason = upassReason;
  }

  public Integer getIspassSup() {
    return ispassSup;
  }

  public void setIspassSup(Integer ispassSup) {
    this.ispassSup = ispassSup;
  }

  public String getDiagnosisTime() {
    return diagnosisTime;
  }

  public void setDiagnosisTime(String diagnosisTime) {
    this.diagnosisTime = diagnosisTime;
  }

  public Integer getBuildingNum() {
    return buildingNum;
  }

  public void setBuildingNum(Integer buildingNum) {
    this.buildingNum = buildingNum;
  }

  public String getEarTag() {
    return earTag;
  }

  public void setEarTag(String earTag) {
    this.earTag = earTag;
  }

  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public Integer getSize() {
    return size;
  }

  public void setSize(Integer size) {
    this.size = size;
  }

  public List<Long> getFactoryList() {
    return factoryList;
  }

  public void setFactoryList(List<Long> factoryList) {
    this.factoryList = factoryList;
  }

  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

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

  @Override
  public String toString() {
    return "DiagnosisRequest{" +
            "id=" + id +
            ", diagnosisResult='" + diagnosisResult + '\'' +
            ", diagnosisMethod='" + diagnosisMethod + '\'' +
            ", dose='" + dose + '\'' +
            ", gmtCreate='" + gmtCreate + '\'' +
            ", gmtModified='" + gmtModified + '\'' +
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
            ", supervisorName='" + supervisorName + '\'' +
            ", professorName='" + professorName + '\'' +
            ", diagnosisTime='" + diagnosisTime + '\'' +
            ", startTime='" + startTime + '\'' +
            ", endTime='" + endTime + '\'' +
            ", buildingNum=" + buildingNum +
            ", earTag='" + earTag + '\'' +
            ", page=" + page +
            ", size=" + size +
            ", factoryList=" + factoryList +
            '}';
  }
}
