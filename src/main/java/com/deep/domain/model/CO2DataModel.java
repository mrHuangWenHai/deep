package com.deep.domain.model;

/**
 * create by zhongrui on 18-4-7.
 */
public class CO2DataModel {

    private int id;
    private int devAddr;
    private int co2;
    private String time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDevAddr() {
        return devAddr;
    }

    public void setDevAddr(int devAddr) {
        this.devAddr = devAddr;
    }

    public int getCo2() {
        return co2;
    }

    public void setCo2(int co2) {
        this.co2 = co2;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
