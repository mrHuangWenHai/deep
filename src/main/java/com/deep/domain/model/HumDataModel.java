package com.deep.domain.model;

/**
 * create by zhongrui on 18-4-7.
 */
public class HumDataModel {
    private int id;
    private int devAddr;
    private float hum;
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

    public float getHum() {
        return hum;
    }

    public void setHum(float hum) {
        this.hum = hum;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
