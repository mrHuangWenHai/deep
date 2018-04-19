package com.deep.domain.model;

/**
 * create by zhongrui on 18-4-7.
 */
public class NH3DataModel {

    private int id;
    private int devAddr;
    private int nh3;
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

    public int getNh3() {
        return nh3;
    }

    public void setNh3(int nh3) {
        this.nh3 = nh3;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
