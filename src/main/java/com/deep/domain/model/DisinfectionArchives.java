package com.deep.domain.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by huangwenhai on 2018/4/16.
 */

public class DisinfectionArchives implements Serializable {

  private int id;
  @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss")
  private Timestamp gmtCreate;
  @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss")
  private Timestamp gmtModified;
  @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss")
  private Timestamp disinfectionTime;
  private String disinfectionPlace;
  private String medicineName;
  private String disinfectionMethod;
  private int dosage;
  private int operatorId;
  private int tecReviewerId;
  private int supervisionId;
  private String remark;

//  public int getId() {
//    return id;
//  }
//
//  public void setId(int id) {
//    this.id = id;
//  }
//
//  public Timestamp getGmtCreate() {
//    return gmtCreate;
//  }
//
//  public void setGmtCreate(Timestamp gmtCreate) {
//    this.gmtCreate = gmtCreate;
//  }
//
//  public Timestamp getGmtModified() {
//    return gmtModified;
//  }
//
//  public void setGmtModified(Timestamp gmtModified) {
//    this.gmtModified = gmtModified;
//  }
//
//  public Timestamp getDisinfectionTime() {
//    return disinfectionTime;
//  }
//
//  public void setDisinfectionTime(Timestamp disinfectionTime) {
//    this.disinfectionTime = disinfectionTime;
//  }
//
//  public String getDisinfectionPlace() {
//    return disinfectionPlace;
//  }
//
//  public void setDisinfectionPlace(String disinfectionPlace) {
//    this.disinfectionPlace = disinfectionPlace;
//  }
//
//  public String getMedicineName() {
//    return medicineName;
//  }
//
//  public void setMedicineName(String medicineName) {
//    this.medicineName = medicineName;
//  }
//
//  public String getDisinfectionMethod() {
//    return disinfectionMethod;
//  }
//
//  public void setDisinfectionMethod(String disinfectionMethod) {
//    this.disinfectionMethod = disinfectionMethod;
//  }
//
//  public int getDosage() {
//    return dosage;
//  }
//
//  public void setDosage(int dosage) {
//    this.dosage = dosage;
//  }
//
//  public int getOperatorId() {
//    return operatorId;
//  }
//
//  public void setOperatorId(int operatorId) {
//    this.operatorId = operatorId;
//  }
//
//  public int getTecReviewerId() {
//    return tecReviewerId;
//  }
//
//  public void setTecReviewerId(int tecReviewerId) {
//    this.tecReviewerId = tecReviewerId;
//  }
//
//  public int getSupervisionId() {
//    return supervisionId;
//  }
//
//  public void setSupervisionId(int supervisionId) {
//    this.supervisionId = supervisionId;
//  }
//
//  public String getRemark() {
//    return remark;
//  }
//
//  public void setRemark(String remark) {
//    this.remark = remark;
//  }
//
//  @Override
//  public String toString() {
//    return "DisinfectionArchives{" +
//        "id=" + id +
//        ", gmtCreate=" + gmtCreate +
//        ", gmtModified=" + gmtModified +
//        ", disinfectionTime=" + disinfectionTime +
//        ", disinfectionPlace='" + disinfectionPlace + '\'' +
//        ", medicineName='" + medicineName + '\'' +
//        ", disinfectionMethod='" + disinfectionMethod + '\'' +
//        ", dosage=" + dosage +
//        ", operatorId=" + operatorId +
//        ", tecReviewerId=" + tecReviewerId +
//        ", supervisionId=" + supervisionId +
//        ", remark='" + remark + '\'' +
//        '}';
//  }
}
