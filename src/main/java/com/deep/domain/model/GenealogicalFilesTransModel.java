package com.deep.domain.model;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * create by zhongrui on 18-3-19.
 */
public class GenealogicalFilesTransModel {
    private Long id;
    private String beforeOwnerFactory;    //上一任拥有人 工厂号 用于追溯
    private String nowOwnerFactory;    //当前拥有人  工厂号
    private String immuneEartag;   //免疫耳牌
    private int transTime;    //转移次数
    private double transWeight;     //转移时体重
    //用于和批量查询表 查询对比
    private Timestamp gmtCreate;    //建表时间
    private Timestamp gmtTrans;    //转移时间

    public GenealogicalFilesTransModel() {
    }

    public GenealogicalFilesTransModel(String beforeOwnerFactory, String nowOwnerFactory, String immuneEartag, int transTime, double transWeight, Timestamp gmtCreate, Timestamp gmtTrans) {
        this.beforeOwnerFactory = beforeOwnerFactory;
        this.nowOwnerFactory = nowOwnerFactory;
        this.immuneEartag = immuneEartag;
        this.transTime = transTime;
        this.transWeight = transWeight;
        this.gmtCreate = gmtCreate;
        this.gmtTrans = gmtTrans;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBeforeOwnerFactory() {
        return beforeOwnerFactory;
    }

    public void setBeforeOwnerFactory(String beforeOwnerFactory) {
        this.beforeOwnerFactory = beforeOwnerFactory;
    }

    public String getNowOwnerFactory() {
        return nowOwnerFactory;
    }

    public void setNowOwnerFactory(String nowOwnerFactory) {
        this.nowOwnerFactory = nowOwnerFactory;
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

    public Timestamp getGmtTrans() {
        return gmtTrans;
    }

    public void setGmtTrans(Timestamp gmtTrans) {
        this.gmtTrans = gmtTrans;
    }
}
