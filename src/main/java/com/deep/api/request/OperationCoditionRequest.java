package com.deep.api.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

/**
 * Created by huangwenhai on 2018/4/17.
 */
public class OperationCoditionRequest {

  private Integer id;
  private Integer techCheckStatus;
  private Integer operationId;
  @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss")
  private Timestamp checkTime;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getTechCheckStatus() {
    return techCheckStatus;
  }

  public void setTechCheckStatus(Integer techCheckStatus) {
    this.techCheckStatus = techCheckStatus;
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

  @Override
  public String toString() {
    return "OperationCoditionRequest{" +
        "id=" + id +
        ", techCheckStatus=" + techCheckStatus +
        ", operationId=" + operationId +
        ", checkTime=" + checkTime +
        '}';
  }
}
