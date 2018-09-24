package com.deep.domain.model;

/**
 * 用于返回环境追溯结果
 * create by zhongrui on 18-4-30.
 */
public class EnvironmentTraceReturnModel {
    private String time;

    private float tempWater;
    private float ph;

    private float pm;

    private float tempSoil;
    private float humSoil;

    private float tempIndoor;
    private float humIndoor;

    private float nh3;

    private String tempWaterReturn;
    private String phReturn;

    private String pmReturn;

    private String tempSoilReturn;
    private String humSoilReturn;

    private String tempIndoorReturn;
    private String humIndoorReturn;

    private String nh3Return;

    public EnvironmentTraceReturnModel(EnvironmentTraceModel environmentTraceModel) {
        this.time = environmentTraceModel.getTime();
        this.tempIndoor = environmentTraceModel.getTempIndoor();
        this.ph = environmentTraceModel.getPh();
        this.pm = environmentTraceModel.getPm();
        this.tempSoil = environmentTraceModel.getTempSoil();
        this.humSoil = environmentTraceModel.getHumSoil();
        this.tempIndoor = environmentTraceModel.getTempIndoor();
        this.humIndoor = environmentTraceModel.getHumIndoor();
        this.nh3 = environmentTraceModel.getNh3();

        //水温
        if (environmentTraceModel.getTempWater() < 10){
            this.tempWaterReturn = "水温过低";
        } else if (environmentTraceModel.getTempIndoor() > 30){
            this.tempWaterReturn = "水温过高";
        } else {
            this.tempWaterReturn = "水温正常";
        }

        //ph
        if (environmentTraceModel.getPh() <= 4.5){
            this.phReturn = "极强酸性";
        } else if (environmentTraceModel.getPh() > 4.5 &&
                environmentTraceModel.getPh() <= 5.5 ){
            this.phReturn = "强酸性";
        } else if (environmentTraceModel.getPh() > 5.5 &&
                environmentTraceModel.getPh() <= 6.5 ){
            this.phReturn = "酸性";
        } else if (environmentTraceModel.getPh() > 6.5 &&
                environmentTraceModel.getPh() <= 7.5 ){
            this.phReturn = "中性";
        } else if (environmentTraceModel.getPh() > 7.5 &&
                environmentTraceModel.getPh() <= 8.5 ){
            this.phReturn = "碱性";
        } else if (environmentTraceModel.getPh() > 8.5 &&
                environmentTraceModel.getPh() <= 9.5 ){
            this.phReturn = "强碱性";
        } else {
            this.phReturn = "极强碱性";
        }

        //pm
        if (environmentTraceModel.getPm() < 35){
            this.pmReturn = "优";
        } else if (environmentTraceModel.getPm() >= 35 &&
                environmentTraceModel.getPm() < 75){
            this.pmReturn = "良";
        } else if (environmentTraceModel.getPm() >= 75 &&
                environmentTraceModel.getPm() < 115){
            this.pmReturn = "轻度污染";
        } else if (environmentTraceModel.getPm() >= 115 &&
                environmentTraceModel.getPm() < 150){
            this.pmReturn = "中度污染";
        } else if (environmentTraceModel.getPm() >= 150 &&
                environmentTraceModel.getPm() < 250){
            this.pmReturn = "重度污染";
        } else if (environmentTraceModel.getPm() >= 250){
            this.pmReturn = "严重污染";
        }

        //土壤温度
        if (environmentTraceModel.getTempSoil() < 10){
            this.tempSoilReturn = "土壤温度过低";
        } else if (environmentTraceModel.getTempSoil() > 30){
            this.tempSoilReturn = "土壤温度过高";
        } else {
            this.tempSoilReturn = "土壤温度正常";
        }

        //土壤湿度
        if (environmentTraceModel.getHumSoil() < 0.7){
            this.humSoilReturn = "土壤湿度正常";
        } else {
            this.humSoilReturn = "土壤湿度过高";
        }

        //羊舍温度
        if (environmentTraceModel.getTempIndoor() < 10){
            this.tempIndoorReturn = "羊舍温度过低";
        } else if (environmentTraceModel.getTempIndoor() > 30){
            this.tempIndoorReturn = "羊舍温度过高";
        } else {
            this.tempIndoorReturn = "羊舍温度正常";
        }

        //室内湿度
        if (environmentTraceModel.getHumIndoor() < 0.7){
            this.humIndoorReturn = "室内湿度正常";
        } else {
            this.humIndoorReturn = "室内湿度过高";
        }


        //nh3
        if ( environmentTraceModel.getNh3() < 30){
            this.nh3Return = "氨气浓度正常";
        } else {
            this.nh3Return = "氨气浓度过高";
        }

    }

    public String getTempIndoorReturn() {
        return tempIndoorReturn;
    }

    public void setTempIndoorReturn(String tempIndoorReturn) {
        this.tempIndoorReturn = tempIndoorReturn;
    }

    public String getTempWaterReturn() {
        return tempWaterReturn;
    }

    public void setTempWaterReturn(String tempWaterReturn) {
        this.tempWaterReturn = tempWaterReturn;
    }

    public String getPhReturn() {
        return phReturn;
    }

    public void setPhReturn(String phReturn) {
        this.phReturn = phReturn;
    }

    public String getTempSoilReturn() {
        return tempSoilReturn;
    }

    public void setTempSoilReturn(String tempSoilReturn) {
        this.tempSoilReturn = tempSoilReturn;
    }

    public String getHumSoilReturn() {
        return humSoilReturn;
    }

    public void setHumSoilReturn(String humSoilReturn) {
        this.humSoilReturn = humSoilReturn;
    }

    public String getHumIndoorReturn() {
        return humIndoorReturn;
    }

    public void setHumIndoorReturn(String humIndoorReturn) {
        this.humIndoorReturn = humIndoorReturn;
    }

    public String getPmReturn() {
        return pmReturn;
    }

    public void setPmReturn(String pmReturn) {
        this.pmReturn = pmReturn;
    }

    public String getNh3Return() {
        return nh3Return;
    }

    public void setNh3Return(String nh3Return) {
        this.nh3Return = nh3Return;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public float getTempWater() {
        return tempWater;
    }

    public void setTempWater(float tempWater) {
        this.tempWater = tempWater;
    }

    public float getPh() {
        return ph;
    }

    public void setPh(float ph) {
        this.ph = ph;
    }

    public float getPm() {
        return pm;
    }

    public void setPm(float pm) {
        this.pm = pm;
    }

    public float getTempSoil() {
        return tempSoil;
    }

    public void setTempSoil(float tempSoil) {
        this.tempSoil = tempSoil;
    }

    public float getHumSoil() {
        return humSoil;
    }

    public void setHumSoil(float humSoil) {
        this.humSoil = humSoil;
    }

    public float getTempIndoor() {
        return tempIndoor;
    }

    public void setTempIndoor(float tempIndoor) {
        this.tempIndoor = tempIndoor;
    }

    public float getHumIndoor() {
        return humIndoor;
    }

    public void setHumIndoor(float humIndoor) {
        this.humIndoor = humIndoor;
    }

    public float getNh3() {
        return nh3;
    }

    public void setNh3(float nh3) {
        this.nh3 = nh3;
    }
}
