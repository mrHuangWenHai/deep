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
  private String gmtCreate;
  @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss")
  private String gmtModified;
  @NotNull
  @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss")
  private String checkTime;
  @NotNull
  private Integer colonyHouse;
  @NotNull
  private Integer warehouseWorkshop;
  @NotNull
  private Integer killWormDeratization;
  @NotNull
  private Integer sterilizingRoom;
  @NotNull
  private Integer operation;
  @NotNull
  private Integer needleSheep;
  @NotNull
  private Integer vaccine;
  @NotNull
  private Integer safetyProtection;
  @NotNull
  private Integer rubbishWater;
  @NotNull
  private Integer operationSpecification;
  @NotNull
  private Integer airTemperature;
  @NotNull
  private Integer exerciseDaylighting;
  @NotNull
  private Integer carDisinfect;
  @Min(0)
  private int operatorId;
  @NotBlank
  private String operatorName;
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

  public String getCheckTime() {
    return checkTime;
  }

  public void setCheckTime(String checkTime) {
    this.checkTime = checkTime;
  }

  public Integer getColonyHouse() {
    return colonyHouse;
  }

  public void setColonyHouse(Integer colonyHouse) {
    this.colonyHouse = colonyHouse;
  }

  public Integer getWarehouseWorkshop() {
    return warehouseWorkshop;
  }

  public void setWarehouseWorkshop(Integer warehouseWorkshop) {
    this.warehouseWorkshop = warehouseWorkshop;
  }

  public Integer getKillWormDeratization() {
    return killWormDeratization;
  }

  public void setKillWormDeratization(Integer killWormDeratization) {
    this.killWormDeratization = killWormDeratization;
  }

  public Integer getSterilizingRoom() {
    return sterilizingRoom;
  }

  public void setSterilizingRoom(Integer sterilizingRoom) {
    this.sterilizingRoom = sterilizingRoom;
  }

  public Integer getOperation() {
    return operation;
  }

  public void setOperation(Integer operation) {
    this.operation = operation;
  }

  public Integer getNeedleSheep() {
    return needleSheep;
  }

  public void setNeedleSheep(Integer needleSheep) {
    this.needleSheep = needleSheep;
  }

  public Integer getVaccine() {
    return vaccine;
  }

  public void setVaccine(Integer vaccine) {
    this.vaccine = vaccine;
  }

  public Integer getSafetyProtection() {
    return safetyProtection;
  }

  public void setSafetyProtection(Integer safetyProtection) {
    this.safetyProtection = safetyProtection;
  }

  public Integer getRubbishWater() {
    return rubbishWater;
  }

  public void setRubbishWater(Integer rubbishWater) {
    this.rubbishWater = rubbishWater;
  }

  public Integer getOperationSpecification() {
    return operationSpecification;
  }

  public void setOperationSpecification(Integer operationSpecification) {
    this.operationSpecification = operationSpecification;
  }

  public Integer getAirTemperature() {
    return airTemperature;
  }

  public void setAirTemperature(Integer airTemperature) {
    this.airTemperature = airTemperature;
  }

  public Integer getExerciseDaylighting() {
    return exerciseDaylighting;
  }

  public void setExerciseDaylighting(Integer exerciseDaylighting) {
    this.exerciseDaylighting = exerciseDaylighting;
  }

  public Integer getCarDisinfect() {
    return carDisinfect;
  }

  public void setCarDisinfect(Integer carDisinfect) {
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

  public String getOperatorName() {
    return operatorName;
  }

  public void setOperatorName(String operatorName) {
    this.operatorName = operatorName;
  }

  @Override
  public String toString() {
    return "OperationFile{" +
        "id=" + id +
        ", gmtCreate='" + gmtCreate + '\'' +
        ", gmtModified='" + gmtModified + '\'' +
        ", checkTime='" + checkTime + '\'' +
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
        ", operatorName='" + operatorName + '\'' +
        ", factoryNum=" + factoryNum +
        ", factoryName='" + factoryName + '\'' +
        ", ispassCheck=" + ispassCheck +
        ", ispassSup=" + ispassSup +
        '}';
  }
}
