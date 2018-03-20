package com.deep.domain.model;

import java.sql.Timestamp;

/**
 * create by zhongrui on 18-3-19.
 */
public class GenealogicalFilesTransModel {
    private String immuneEartag;   //免疫耳牌
    private int transTime;    //转移次数
    private double transWeight;     //转移时体重
    private String nextOwner;    //下一任拥有人
    private Timestamp gmtCreate;

    public GenealogicalFilesTransModel(String immuneEartag, int transTime, double transWeight, String nextOwner, Timestamp gmtCreate) {
        this.immuneEartag = immuneEartag;
        this.transTime = transTime;
        this.transWeight = transWeight;
        this.nextOwner = nextOwner;
        this.gmtCreate = gmtCreate;
    }

    public String getImmuneEartag() {
        return immuneEartag;
    }

    public void setImmuneEartag(String immuneEartag) {
        this.immuneEartag = immuneEartag;
    }

    public int getTransTime() {
        return transTime;
    }

    public void setTransTime(int transTime) {
        this.transTime = transTime;
    }

    public double getTransWeight() {
        return transWeight;
    }

    public void setTransWeight(double transWeight) {
        this.transWeight = transWeight;
    }

    public String getNextOwner() {
        return nextOwner;
    }

    public void setNextOwner(String nextOwner) {
        this.nextOwner = nextOwner;
    }

    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
