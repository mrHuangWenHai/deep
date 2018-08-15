package com.deep.domain.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class MoveRecord {
    private int id;
    @JSONField(name = "factory")
    private int factory;
    @JSONField(name = "brand")
    private String brand;
    @JSONField(name = "src_b")
    private int src_b;
    @JSONField(name = "src_c")
    private int src_c;
    @JSONField(name = "dst_b")
    private int dst_b;
    @JSONField(name = "dst_c")
    private int dst_c;
    @JSONField(name = "date")
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFactory() {
        return factory;
    }

    public void setFactory(int factory) {
        this.factory = factory;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getSrc_b() {
        return src_b;
    }

    public void setSrc_b(int src_b) {
        this.src_b = src_b;
    }

    public int getSrc_c() {
        return src_c;
    }

    public void setSrc_c(int src_c) {
        this.src_c = src_c;
    }

    public int getDst_b() {
        return dst_b;
    }

    public void setDst_b(int dst_b) {
        this.dst_b = dst_b;
    }

    public int getDst_c() {
        return dst_c;
    }

    public void setDst_c(int dst_c) {
        this.dst_c = dst_c;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
