package com.deep.api.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by huangwenhai on 2018/4/17.
 */
public class OperationCoditionRequest {

  private Integer id;
  private Integer ispassCheck;
  private Integer ispassSup;
  private Integer operationId;
  @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss")
  private Timestamp checkTime;
  private Long factoryNum;
  private String factoryName;
  private List<Long> factoryList;
  private int page = 0;
//  private int pageSize = 10;

  private int size = 10;
  private String startTime;
  private String endTime;
  private String earTag;

  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
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

  public String getEarTag() {
    return earTag;
  }

  public void setEarTag(String earTag) {
    this.earTag = earTag;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {

    this.id = id;
  }

  public Integer getIspassCheck() {
    return ispassCheck;
  }

  public void setIspassCheck(Integer ispassCheck) {
    this.ispassCheck = ispassCheck;
  }

  public Integer getOperationId() {
    return operationId;
  }

  public void setOperationId(Integer operationId) {
    this.operationId = operationId;
  }

  public Timestamp getCheckTime() {
    return checkTime;
  }

  public void setCheckTime(Timestamp checkTime) {
    this.checkTime = checkTime;
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

  public List<Long> getFactoryList() {
    return factoryList;
  }

  public void setFactoryList(List<Long> factoryList) {
    this.factoryList = factoryList;
  }

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

//  public int getPageSize() {
//    return pageSize;
//  }
//
//  public void setPageSize(int pageSize) {
//    this.pageSize = pageSize;
//  }

  public Integer getIspassSup() {
    return ispassSup;
  }

  public void setIspassSup(Integer ispassSup) {
    this.ispassSup = ispassSup;
  }

  @Override
  public String toString() {
    return "OperationCoditionRequest{" +
            "id=" + id +
            ", ispassCheck=" + ispassCheck +
            ", ispassSup=" + ispassSup +
            ", operationId=" + operationId +
            ", checkTime=" + checkTime +
            ", factoryNum=" + factoryNum +
            ", factoryName='" + factoryName + '\'' +
            ", factoryList=" + factoryList +
            ", page=" + page +
            ", size=" + size +
            ", startTime='" + startTime + '\'' +
            ", endTime='" + endTime + '\'' +
            ", earTag='" + earTag + '\'' +
            '}';
  }
}
