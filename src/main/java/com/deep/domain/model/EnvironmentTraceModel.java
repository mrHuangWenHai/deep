package com.deep.domain.model;

import java.math.BigInteger;

/**
 * create by zhongrui on 18-4-17.
 */
public class EnvironmentTraceModel {
    private long id;
    private String time;
    private BigInteger factoryNum;

    private float tempWater;
    private float ph;

    private float pm;

    private float tempSoil;
    private float humSoil;

    private float tempIndoor;
    private float humIndoor;

    private float nh3;

    public float getTempSoil() {
        return tempSoil;
    }

    public void setTempSoil(float tempSoil) {
        this.tempSoil = tempSoil;
    }

    public float getHumIndoor() {
        return humIndoor;
    }

    public void setHumIndoor(float humIndoor) {
        this.humIndoor = humIndoor;
    }

    public float getHumSoil() {
        return humSoil;
    }

    public void setHumSoil(float humSoil) {
        this.humSoil = humSoil;
    }

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

    @Override
    public String toString() {
        return "EnvironmentTraceModel{" +
                "id=" + id +
                ", time='" + time + '\'' +
                ", factoryNum=" + factoryNum +
                ", tempIndoor=" + tempIndoor +
                ", tempWater=" + tempWater +
                ", tempSoil=" + tempSoil +
                ", humIndoor=" + humIndoor +
                ", humSoil=" + humSoil +
                ", ph=" + ph +
                ", pm=" + pm +
                ", nh3=" + nh3 +
                '}';
    }
}
