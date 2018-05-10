package com.deep.api.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.List;

/**
 * create by zhongrui on 18-4-18.
 */
public class GenealogicalRequest {

    private Long id;
    private Long factoryNum;  //工厂号
    private String nativeEartag;  //原耳牌
    private String immuneEartag;  //免疫耳牌
    private String tradeMarkEartag;  //商标耳牌
    private String type;     //品种名

    private String brief;    //对该品种的简介 查询时返回
    private String breedingSheepBase;  //种羊基地
    private String birthTime;  //出登时间or出生时间
    private Float birthWeight;  //初登体重
    private String color;  //颜色
    private String sex;   //性别
    private String eartagOfFather;  //父耳牌
    private String eartagOfMother;  //母耳牌
    private String eartagOfFathersFather;  //父父耳牌
    private String eartagOfFathersMother;  //父母耳牌
    private String eartagOfMothersFather;  //母父耳牌
    private String eartagOfMothersMother;  //母母耳牌
    private String remark;   //备注

    private String gmtCreate;     //建立时间

    private String gmtModified;   //修改时间
    private int operatorId;       //操作员id
    private String operatorName;    //操作员姓名


    //查询关键字
    @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss")
    private String startTime;
    @JsonFormat(pattern = "yyy-MM-dd HH:mm:ss")
    private String endTime;
    private String birthWeightStart;
    private String birthWeightEnd;
    private int page = 0;
    private int size = 10;

    private List<Long> factoryList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBirthWeight(Float birthWeight) {
        this.birthWeight = birthWeight;
    }

    public Long getFactoryNum() {
        return factoryNum;
    }

    public void setFactoryNum(Long factoryNum) {
        this.factoryNum = factoryNum;
    }

    public String getNativeEartag() {
        return nativeEartag;
    }

    public void setNativeEartag(String nativeEartag) {
        this.nativeEartag = nativeEartag;
    }

    public String getImmuneEartag() {
        return immuneEartag;
    }

    public void setImmuneEartag(String immuneEartag) {
        this.immuneEartag = immuneEartag;
    }

    public String getTradeMarkEartag() {
        return tradeMarkEartag;
    }

    public void setTradeMarkEartag(String tradeMarkEartag) {
        this.tradeMarkEartag = tradeMarkEartag;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }


    public Float getBirthWeight() {
        return birthWeight;
    }


    public String getBreedingSheepBase() {
        return breedingSheepBase;
    }

    public void setBreedingSheepBase(String breedingSheepBase) {
        this.breedingSheepBase = breedingSheepBase;
    }

    public String getBirthTime() {
        return birthTime;
    }

    public void setBirthTime(String birthTime) {
        this.birthTime = birthTime;
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEartagOfFather() {
        return eartagOfFather;
    }

    public void setEartagOfFather(String eartagOfFather) {
        this.eartagOfFather = eartagOfFather;
    }

    public String getEartagOfMother() {
        return eartagOfMother;
    }

    public void setEartagOfMother(String eartagOfMother) {
        this.eartagOfMother = eartagOfMother;
    }

    public String getEartagOfFathersFather() {
        return eartagOfFathersFather;
    }

    public void setEartagOfFathersFather(String eartagOfFathersFather) {
        this.eartagOfFathersFather = eartagOfFathersFather;
    }

    public String getEartagOfFathersMother() {
        return eartagOfFathersMother;
    }

    public void setEartagOfFathersMother(String eartagOfFathersMother) {
        this.eartagOfFathersMother = eartagOfFathersMother;
    }

    public String getEartagOfMothersFather() {
        return eartagOfMothersFather;
    }

    public void setEartagOfMothersFather(String eartagOfMothersFather) {
        this.eartagOfMothersFather = eartagOfMothersFather;
    }

    public String getEartagOfMothersMother() {
        return eartagOfMothersMother;
    }

    public void setEartagOfMothersMother(String eartagOfMothersMother) {
        this.eartagOfMothersMother = eartagOfMothersMother;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(String gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }


    public String getBirthWeightStart() {
        return birthWeightStart;
    }

    public void setBirthWeightStart(String birthWeightStart) {
        this.birthWeightStart = birthWeightStart;
    }

    public String getBirthWeightEnd() {
        return birthWeightEnd;
    }

    public void setBirthWeightEnd(String birthWeightEnd) {
        this.birthWeightEnd = birthWeightEnd;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Long> getFactoryList() {
        return factoryList;
    }

    public void setFactoryList(List<Long> factoryList) {
        this.factoryList = factoryList;
    }

    @Override
    public String toString() {
        return "GenealogicalRequest{" +
            "id=" + id +
            ", factoryNum=" + factoryNum +
            ", nativeEartag='" + nativeEartag + '\'' +
            ", immuneEartag='" + immuneEartag + '\'' +
            ", tradeMarkEartag='" + tradeMarkEartag + '\'' +
            ", type='" + type + '\'' +
            ", brief='" + brief + '\'' +
            ", breedingSheepBase='" + breedingSheepBase + '\'' +
            ", birthTime='" + birthTime + '\'' +
            ", birthWeight=" + birthWeight +
            ", color='" + color + '\'' +
            ", sex='" + sex + '\'' +
            ", eartagOfFather='" + eartagOfFather + '\'' +
            ", eartagOfMother='" + eartagOfMother + '\'' +
            ", eartagOfFathersFather='" + eartagOfFathersFather + '\'' +
            ", eartagOfFathersMother='" + eartagOfFathersMother + '\'' +
            ", eartagOfMothersFather='" + eartagOfMothersFather + '\'' +
            ", eartagOfMothersMother='" + eartagOfMothersMother + '\'' +
            ", remark='" + remark + '\'' +
            ", gmtCreate='" + gmtCreate + '\'' +
            ", gmtModified='" + gmtModified + '\'' +
            ", operatorId=" + operatorId +
            ", operatorName='" + operatorName + '\'' +
            ", startTime='" + startTime + '\'' +
            ", endTime='" + endTime + '\'' +
            ", birthWeightStart='" + birthWeightStart + '\'' +
            ", birthWeightEnd='" + birthWeightEnd + '\'' +
            ", page=" + page +
            ", size=" + size +
            ", factoryList=" + factoryList +
            '}';
    }
}
