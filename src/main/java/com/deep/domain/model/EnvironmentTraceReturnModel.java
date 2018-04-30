package com.deep.domain.model;

/**
 * 用于返回环境追溯结果
 * create by zhongrui on 18-4-30.
 */
public class EnvironmentTraceReturnModel {
    private String tempIndoorReturn;
    private String tempWaterReturn;
    private String phReturn;
    private String humReturn;
    private String pmReturn;
    private String nh3Return;

    public EnvironmentTraceReturnModel(EnvironmentTraceModel environmentTraceModel) {
        //羊舍温度
        if (environmentTraceModel.getTempIndoor() < 10){
            this.tempIndoorReturn = "羊舍温度过低";
        } else if (environmentTraceModel.getTempIndoor() > 30){
            this.tempIndoorReturn = "羊舍温度过高";
        } else {
            this.tempIndoorReturn = "羊舍温度正常";
        }

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

        //湿度
        if (environmentTraceModel.getHum() < 0.7){
            this.humReturn = "湿度正常";
        } else {
            this.humReturn = "湿度过高";
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

    public String getHumReturn() {
        return humReturn;
    }

    public void setHumReturn(String humReturn) {
        this.humReturn = humReturn;
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
}
