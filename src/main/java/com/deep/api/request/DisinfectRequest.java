package com.deep.api.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigInteger;
import java.util.List;

/**
 * create by zhongrui on 18-4-18.
 */
public class DisinfectRequest {

    int id;
    private String disinfectEartag;
    private Long factoryNum;     //工厂号
    private String factoryName;
    private String disinfectTime;     //消毒时间
    private String disinfectName;     //消毒药品名称
    private String dose;    //用药剂量
    private String disinfectWay;     //消毒方法
    private Integer operatorId;
    private String operatorName;      //操作员(创建表操作人员)
    private String professor;       //技术审核(审核表人员专家) 可空
    private String supervisor;       //监督员(监督操作员人员) 可空
    private String name;
    private String remark;        //备注
    private Integer ispassCheck;       //是否通过审核 可空 默认为 0
    private String unpassReason;   //未通过审核原因 可空 默认未 无
    private Integer ispassSup;       //是否通过审核 可空 默认为 0
    private String gmtCreate;   //创建时间
    private String gmtModified;     //修改时间
    private String gmtProfessor;     //审核时间 可空
    private String gmtSupervise;     //监督确认时间 可空
    @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss")
    private String startTime;
    @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss")
    private String endTime;
    private int page = 0;
    private int size = 10;
    private List<Long>factoryList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisinfectEartag() {
        return disinfectEartag;
    }

    public void setDisinfectEartag(String disinfectEartag) {
        this.disinfectEartag = disinfectEartag;
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


    public String getDisinfectTime() {
        return disinfectTime;
    }

    public void setDisinfectTime(String disinfectTime) {
        this.disinfectTime = disinfectTime;
    }

    public String getDisinfectName() {
        return disinfectName;
    }

    public void setDisinfectName(String disinfectName) {
        this.disinfectName = disinfectName;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getDisinfectWay() {
        return disinfectWay;
    }

    public void setDisinfectWay(String disinfectWay) {
        this.disinfectWay = disinfectWay;
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

    public String getUnpassReason() {
        return unpassReason;
    }

    public void setUnpassReason(String unpassReason) {
        this.unpassReason = unpassReason;
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

    public Integer getIspassCheck() {
        return ispassCheck;
    }

    public void setIspassCheck(Integer ispassCheck) {
        this.ispassCheck = ispassCheck;
    }

    public Integer getIspassSup() {
        return ispassSup;
    }

    public void setIspassSup(Integer ispassSup) {
        this.ispassSup = ispassSup;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Long> getFactoryList() {
        return factoryList;
    }

    public void setFactoryList(List<Long> factoryList) {
        this.factoryList = factoryList;
    }

  @Override
  public String toString() {
    return "DisinfectRequest{" +
        "id=" + id +
        ", disinfectEartag='" + disinfectEartag + '\'' +
        ", factoryNum=" + factoryNum +
        ", factoryName='" + factoryName + '\'' +
        ", disinfectTime='" + disinfectTime + '\'' +
        ", disinfectName='" + disinfectName + '\'' +
        ", dose='" + dose + '\'' +
        ", disinfectWay='" + disinfectWay + '\'' +
        ", operatorId=" + operatorId +
        ", operatorName='" + operatorName + '\'' +
        ", professor='" + professor + '\'' +
        ", supervisor='" + supervisor + '\'' +
        ", remark='" + remark + '\'' +
        ", ispassCheck=" + ispassCheck +
        ", unpassReason='" + unpassReason + '\'' +
        ", ispassSup=" + ispassSup +
        ", gmtCreate='" + gmtCreate + '\'' +
        ", gmtModified='" + gmtModified + '\'' +
        ", gmtProfessor='" + gmtProfessor + '\'' +
        ", gmtSupervise='" + gmtSupervise + '\'' +
        ", startTime='" + startTime + '\'' +
        ", endTime='" + endTime + '\'' +
        ", page=" + page +
        ", size=" + size +
        ", factoryList=" + factoryList +
        '}';
  }
}
