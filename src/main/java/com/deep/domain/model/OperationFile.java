package com.deep.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;


import javax.validation.constraints.*;
import java.sql.Timestamp;

/**
 * Created by huangwenhai on 2018/4/17.
 */

public class OperationFile {
  private int id;
  @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss")
  private Timestamp gmtCreate;
  @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss")
  private Timestamp gmtModified;
  @NotNull
  @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss")
  private Timestamp checkTime;
  @NotNull
  private Boolean colonyHouse;
  @NotNull
  private Boolean warehouseWorkshop;
  @NotNull
  private Boolean killWormDeratization;
  @NotNull
  private Boolean sterilizingRoom;
  @NotNull
  private Boolean operation;
  @NotNull
  private Boolean needleSheep;
  @NotNull
  private Boolean vaccine;
  @NotNull
  private Boolean safetyProtection;
  @NotNull
  private Boolean rubbishWater;
  @NotNull
  private Boolean operationSpecification;
  @NotNull
  private Boolean airTemperature;
  @NotNull
  private Boolean exerciseDaylighting;
  @NotNull
  private Boolean carDisinfect;
  @Min(0)
  private int operatorId;
  @Min(0)
  private int factoryNum;
  @NotNull
  private String factoryName;

  int ispassCheck;
  int ispassSup;


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Timestamp getGmtCreate() {
    return gmtCreate;
  }

  public void setGmtCreate(Timestamp gmtCreate) {
    this.gmtCreate = gmtCreate;
  }

  public Timestamp getGmtModified() {
    return gmtModified;
  }

  public void setGmtModified(Timestamp gmtModified) {
    this.gmtModified = gmtModified;
  }

  public Timestamp getCheckTime() {
    return checkTime;
  }

  public void setCheckTime(Timestamp checkTime) {
    this.checkTime = checkTime;
  }

  public Boolean getColonyHouse() {
    return colonyHouse;
  }

  public void setColonyHouse(Boolean colonyHouse) {
    this.colonyHouse = colonyHouse;
  }

  public Boolean getWarehouseWorkshop() {
    return warehouseWorkshop;
  }

  public void setWarehouseWorkshop(Boolean warehouseWorkshop) {
    this.warehouseWorkshop = warehouseWorkshop;
  }

  public Boolean getKillWormDeratization() {
    return killWormDeratization;
  }

  public void setKillWormDeratization(Boolean killWormDeratization) {
    this.killWormDeratization = killWormDeratization;
  }

  public Boolean getSterilizingRoom() {
    return sterilizingRoom;
  }

  public void setSterilizingRoom(Boolean sterilizingRoom) {
    this.sterilizingRoom = sterilizingRoom;
  }

  public Boolean getOperation() {
    return operation;
  }

  public void setOperation(Boolean operation) {
    this.operation = operation;
  }

  public Boolean getNeedleSheep() {
    return needleSheep;
  }

  public void setNeedleSheep(Boolean needleSheep) {
    this.needleSheep = needleSheep;
  }

  public Boolean getVaccine() {
    return vaccine;
  }

  public void setVaccine(Boolean vaccine) {
    this.vaccine = vaccine;
  }

  public Boolean getSafetyProtection() {
    return safetyProtection;
  }

  public void setSafetyProtection(Boolean safetyProtection) {
    this.safetyProtection = safetyProtection;
  }

  public Boolean getRubbishWater() {
    return rubbishWater;
  }

  public void setRubbishWater(Boolean rubbishWater) {
    this.rubbishWater = rubbishWater;
  }

  public Boolean getOperationSpecification() {
    return operationSpecification;
  }

  public void setOperationSpecification(Boolean operationSpecification) {
    this.operationSpecification = operationSpecification;
  }

  public Boolean getAirTemperature() {
    return airTemperature;
  }

  public void setAirTemperature(Boolean airTemperature) {
    this.airTemperature = airTemperature;
  }

  public Boolean getExerciseDaylighting() {
    return exerciseDaylighting;
  }

  public void setExerciseDaylighting(Boolean exerciseDaylighting) {
    this.exerciseDaylighting = exerciseDaylighting;
  }

  public Boolean getCarDisinfect() {
    return carDisinfect;
  }

  public void setCarDisinfect(Boolean carDisinfect) {
    this.carDisinfect = carDisinfect;
  }

  public int getOperatorId() {
    return operatorId;
  }

  public void setOperatorId(int operatorId) {
    this.operatorId = operatorId;
  }

  public int getFactoryNum() {
    return factoryNum;
  }

  public void setFactoryNum(int factoryNum) {
    this.factoryNum = factoryNum;
  }

  public String getFactoryName() {
    return factoryName;
  }

  public void setFactoryName(String factoryName) {
    this.factoryName = factoryName;
  }

  public int getIspassCheck() {
    return ispassCheck;
  }

  public void setIspassCheck(int ispassCheck) {
    this.ispassCheck = ispassCheck;
  }

  public int getIspassSup() {
    return ispassSup;
  }

  public void setIspassSup(int ispassSup) {
    this.ispassSup = ispassSup;
  }

  @Override
  public String toString() {
    return "OperationFile{" +
        "id=" + id +
        ", gmtCreate=" + gmtCreate +
        ", gmtModified=" + gmtModified +
        ", checkTime=" + checkTime +
        ", colonyHouse=" + colonyHouse +
        ", warehouseWorkshop=" + warehouseWorkshop +
        ", killWormDeratization=" + killWormDeratization +
        ", sterilizingRoom=" + sterilizingRoom +
        ", operation=" + operation +
        ", needleSheep=" + needleSheep +
        ", vaccine=" + vaccine +
        ", safetyProtection=" + safetyProtection +
        ", rubbishWater=" + rubbishWater +
        ", operationSpecification=" + operationSpecification +
        ", airTemperature=" + airTemperature +
        ", exerciseDaylighting=" + exerciseDaylighting +
        ", carDisinfect=" + carDisinfect +
        ", operatorId=" + operatorId +
        ", factoryNum=" + factoryNum +
        ", factoryName='" + factoryName + '\'' +
        ", ispassCheck=" + ispassCheck +
        ", ispassSup=" + ispassSup +
        '}';
  }
}
