package com.deep.domain.model;

import java.sql.Timestamp;

/**
 * create by zhongrui on 18-3-19.
 */
public class GenealogicalFilesTransModel {
    private int id;
    private String beforeOwnerFactory;    //上一任拥有人 工厂号 用于追溯
    private String nextOwnerFactory;    //下一任拥有人  工厂号
    private String immuneEartag;   //免疫耳牌
    private int transTime;    //转移次数
    private double transWeight;     //转移时体重
    private Timestamp gmtCreate;

    public GenealogicalFilesTransModel() {
    }

    public GenealogicalFilesTransModel(int id,String beforeOwnerFactory, String nextOwnerFactory, String immuneEartag, int transTime, double transWeight, Timestamp gmtCreate) {
        this.id = id;
        this.beforeOwnerFactory = beforeOwnerFactory;
        this.nextOwnerFactory = nextOwnerFactory;
        this.immuneEartag = immuneEartag;
        this.transTime = transTime;
        this.transWeight = transWeight;
        this.gmtCreate = gmtCreate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBeforeOwnerFactory() {
        return beforeOwnerFactory;
    }

    public void setBeforeOwnerFactory(String beforeOwnerFactory) {
        this.beforeOwnerFactory = beforeOwnerFactory;
    }

    public String getNextOwnerFactory() {
        return nextOwnerFactory;
    }

    public void setNextOwnerFactory(String nextOwnerFactory) {
        this.nextOwnerFactory = nextOwnerFactory;
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

    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}
