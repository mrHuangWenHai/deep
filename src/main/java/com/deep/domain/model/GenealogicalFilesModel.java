package com.deep.domain.model;


import javax.validation.constraints.*;
/**
 * create by zhongrui on 2018/2/1
 **/
public class GenealogicalFilesModel {


    private Integer id;
    @Min(0)
    private Long factoryNum;

    @NotEmpty
    @NotNull
    private String nativeEartag;  //原耳牌
    @NotEmpty
    @NotNull
    private String immuneEartag;  //免疫耳牌
    @NotEmpty
    @NotNull
    @Size(min = 15, max = 15, message = "trademarkEartag need size:15 ")
    @Pattern(regexp = "^[0-9]+$", message = "商标耳牌由数字组成15位")
    private String tradeMarkEartag;  //商标耳牌
    @NotEmpty
    @NotNull
    private String typeName;     //品种名

    private String brief;    //对该品种的简介 查询时返回
    @NotEmpty
    @NotNull
    private String breedingSheepBase;  //种羊基地
    @NotEmpty
    @NotNull
    private String birthTime;  //出登时间or出生时间
    @DecimalMin(value = "0.0")
    private Float birthWeight;  //初登体重
    @NotEmpty
    @NotNull
    private String color;  //颜色
    @NotNull
    private Integer sex;   //性别

    @NotEmpty
    @NotNull
    private String eartagOfFather;  //父耳牌
    @NotEmpty
    @NotNull
    private String eartagOfMother;  //母耳牌
    @NotEmpty
    @NotNull
    private String eartagOfFathersFather;  //父父耳牌
    @NotEmpty
    @NotNull
    private String eartagOfFathersMother;  //父母耳牌
    @NotEmpty
    @NotNull
    private String eartagOfMothersFather;  //母父耳牌
    @NotEmpty
    @NotNull
    private String eartagOfMothersMother;  //母母耳牌
    @Min(0)
    private int operatorId;
    @NotEmpty
    @NotNull
    private String operatorName;

    private String remark;   //备注

    private String gmtCreate;     //建立时间

    private String gmtModified;   //修改时间



    public GenealogicalFilesModel() {

    }

    public GenealogicalFilesModel(GenealogicalFilesModel genealogicalFilesModel) {

      this.id = genealogicalFilesModel.getId();
      this.immuneEartag = genealogicalFilesModel.getImmuneEartag();
      this.nativeEartag = genealogicalFilesModel.getNativeEartag();
      this.tradeMarkEartag = genealogicalFilesModel.getTradeMarkEartag();
      this.breedingSheepBase = genealogicalFilesModel.getBreedingSheepBase();
      this.birthTime = genealogicalFilesModel.getBirthTime();
      this.birthWeight = genealogicalFilesModel.getBirthWeight();
      this.color = genealogicalFilesModel.getColor();
      this.sex = genealogicalFilesModel.getSex();
      this.eartagOfFather = genealogicalFilesModel.getEartagOfFather();
      this.eartagOfMother = genealogicalFilesModel.getEartagOfMother();
      this.eartagOfFathersFather = genealogicalFilesModel.getEartagOfFathersFather();
      this.eartagOfFathersMother = genealogicalFilesModel.getEartagOfFathersMother();
      this.eartagOfMothersFather = genealogicalFilesModel.getEartagOfMothersFather();
      this.eartagOfMothersMother = genealogicalFilesModel.getEartagOfMothersMother();
      this.remark = genealogicalFilesModel.getRemark();
      this.gmtCreate = genealogicalFilesModel.getGmtCreate();
      this.gmtModified = genealogicalFilesModel.getGmtModified();


    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
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


    public Float getBirthWeight() {
        return birthWeight;
    }

    public void setBirthWeight(Float birthWeight) {

        this.birthWeight = birthWeight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {

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


    @Override
    public String toString() {
        return "GenealogicalFilesModel{" +
            "id=" + id +
            ", nativeEartag='" + nativeEartag + '\'' +
            ", immuneEartag='" + immuneEartag + '\'' +
            ", tradeMarkEartag='" + tradeMarkEartag + '\'' +
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
            '}';
    }
}