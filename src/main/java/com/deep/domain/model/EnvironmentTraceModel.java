package com.deep.domain.model;

import java.math.BigInteger;

/**
 * create by zhongrui on 18-4-17.
 */
public class EnvironmentTraceModel {
    private long id;
    private String time;
    private BigInteger factoryNum;
    private float tempIndoor;
    private float tempWater;
    private float ph;
    private float hum;
    private float pm;
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

    public float getTempIndoor() {
        return tempIndoor;
    }

    public void setTempIndoor(float tempIndoor) {
        this.tempIndoor = tempIndoor;
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

    public float getHum() {
        return hum;
    }

    public void setHum(float hum) {
        this.hum = hum;
    }

    public float getPm() {
        return pm;
    }

    public void setPm(float pm) {
        this.pm = pm;
    }

    public float getNh3() {
        return nh3;
    }

    public void setNh3(float nh3) {
        this.nh3 = nh3;
    }
}
