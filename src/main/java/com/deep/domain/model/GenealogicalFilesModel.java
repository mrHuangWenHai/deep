package com.deep.domain.model;

import java.math.BigInteger;
import java.sql.Timestamp;


/**
 * create by zhongrui on 2018/2/1
 **/
public class GenealogicalFilesModel {

    private BigInteger id;
    private String selfEartag;  //8位表示原耳牌
    private String immuneEartag;  //免疫耳牌
    private String tradeMarkEartag;  //商标耳牌
    private String breedingSheepBase;  //种羊基地
    private String birthTime;  //出登时间or出生时间
    private float birthWeight;  //初登体重
    private String color;  //颜色
    private String sex;   //性别
    private String eartagOfFather;  //父耳牌
    private String eartagOfMother;  //母耳牌
    private String eartagOfFathersFather;  //父父耳牌
    private String eartagOfFathersMother;  //父母耳牌
    private String eartagOfMothersFather;  //母父耳牌
    private String eartagOfMothersMother;  //母母耳牌
    private String remark;   //备注
    private Timestamp gmtCreate;     //建立时间
    private Timestamp gmtModified;   //修改时间

    //从前台传递的参数
    //目标:json格式
    private String birthTimeStart;
    private String birthTimeEnd;
    private String birthWeightStart;
    private String birthWeightEnd;

    public GenealogicalFilesModel() {
    }

    public GenealogicalFilesModel(String selfEartag, String immuneEartag, String tradeMarkEartag, String breedingSheepBase, String birthTime, float birthWeight, String color, String sex, String eartagOfFather, String eartagOfMother, String eartagOfFathersFather, String eartagOfFathersMother, String eartagOfMothersFather, String eartagOfMothersMother, String remark) {

        this.selfEartag = selfEartag;
        this.immuneEartag = immuneEartag;
        this.tradeMarkEartag = tradeMarkEartag;
        this.breedingSheepBase = breedingSheepBase;
        this.birthTime = birthTime;
        this.birthWeight = birthWeight;
        this.color = color;
        this.sex = sex;
        this.eartagOfFather = eartagOfFather;
        this.eartagOfMother = eartagOfMother;
        this.eartagOfFathersFather = eartagOfFathersFather;
        this.eartagOfFathersMother = eartagOfFathersMother;
        this.eartagOfMothersFather = eartagOfMothersFather;
        this.eartagOfMothersMother = eartagOfMothersMother;
        this.remark = remark;
    }
    public GenealogicalFilesModel( String selfEartag, String immuneEartag, String tradeMarkEartag, String breedingSheepBase, String birthTime, float birthWeight, String color, String sex, String eartagOfFather, String eartagOfMother, String eartagOfFathersFather, String eartagOfFathersMother, String eartagOfMothersFather, String eartagOfMothersMother, String remark,Timestamp gmtCreate) {

        this.selfEartag = selfEartag;
        this.immuneEartag = immuneEartag;
        this.tradeMarkEartag = tradeMarkEartag;
        this.breedingSheepBase = breedingSheepBase;
        this.birthTime = birthTime;
        this.birthWeight = birthWeight;
        this.color = color;
        this.sex = sex;
        this.eartagOfFather = eartagOfFather;
        this.eartagOfMother = eartagOfMother;
        this.eartagOfFathersFather = eartagOfFathersFather;
        this.eartagOfFathersMother = eartagOfFathersMother;
        this.eartagOfMothersFather = eartagOfMothersFather;
        this.eartagOfMothersMother = eartagOfMothersMother;
        this.remark = remark;
        this.gmtCreate = gmtCreate;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getSelfEartag() {
        return selfEartag;
    }

    public void setSelfEartag(String selfEartag) {
        this.selfEartag = selfEartag;
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

    public float getBirthWeight() {
        return birthWeight;
    }

    public void setBirthWeight(float birthWeight) {
        this.birthWeight = birthWeight;
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

    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Timestamp getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Timestamp gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getBirthTimeStart() {
        return birthTimeStart;
    }

    public void setBirthTimeStart(String birthTimeStart) {
        this.birthTimeStart = birthTimeStart;
    }

    public String getBirthTimeEnd() {
        return birthTimeEnd;
    }

    public void setBirthTimeEnd(String birthTimeEnd) {
        this.birthTimeEnd = birthTimeEnd;
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
}