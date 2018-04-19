package com.deep.domain.model;

import java.math.BigInteger;

/**
 * create by zhongrui on 18-4-17.
 */
public class EnvironmentModel {
    private long id;
    private String time;
    private BigInteger factoryNum;
    private float inTemp;
    private float inHum;
    private float pm;
    private float waterPH;
    private float waterTemp;
    private float soilPH;
    private float soilTemp;
    private float soilHum;
    private float nh3;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public BigInteger getFactoryNum() {
        return factoryNum;
    }

    public void setFactoryNum(BigInteger factoryNum) {
        this.factoryNum = factoryNum;
    }

    public float getInTemp() {
        return inTemp;
    }

    public void setInTemp(float inTemp) {
        this.inTemp = inTemp;
    }

    public float getInHum() {
        return inHum;
    }

    public void setInHum(float inHum) {
        this.inHum = inHum;
    }

    public float getPm() {
        return pm;
    }

    public void setPm(float pm) {
        this.pm = pm;
    }

    public float getWaterPH() {
        return waterPH;
    }

    public void setWaterPH(float waterPH) {
        this.waterPH = waterPH;
    }

    public float getWaterTemp() {
        return waterTemp;
    }

    public void setWaterTemp(float waterTemp) {
        this.waterTemp = waterTemp;
    }

    public float getSoilPH() {
        return soilPH;
    }

    public void setSoilPH(float soilPH) {
        this.soilPH = soilPH;
    }

    public float getSoilTemp() {
        return soilTemp;
    }

    public void setSoilTemp(float soilTemp) {
        this.soilTemp = soilTemp;
    }

    public float getSoilHum() {
        return soilHum;
    }

    public void setSoilHum(float soilHum) {
        this.soilHum = soilHum;
    }

    public float getNh3() {
        return nh3;
    }

    public void setNh3(float nh3) {
        this.nh3 = nh3;
    }
}
