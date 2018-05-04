package com.deep.domain.model;

/**
 * create by zhongrui on 18-4-7.
 */
public class TempDataModel {

    private int id;
    private int devAddr;
    private float temp;
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

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
