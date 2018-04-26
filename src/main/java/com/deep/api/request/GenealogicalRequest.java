package com.deep.api.request;

/**
 * create by zhongrui on 18-4-18.
 */
public class GenealogicalRequest {
    private int id;
    private String nativeEartag;  //原耳牌
    private String immuneEartag;  //免疫耳牌
    private String tradeMarkEartag;  //商标耳牌
    private String type;     //品种名

    private String brief;    //对该品种的简介 查询时返回
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

    private String gmtCreate;     //建立时间

    private String gmtModified;   //修改时间

    //查询关键字
    private String birthTimeStart;
    private String birthTimeEnd;
    private String birthWeightStart;
    private String birthWeightEnd;
    private int page;
    private int size;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
