package com.deep.domain.model;


import java.math.BigInteger;

public class DisinfectFilesModel {
    private Long id;      //id
    private String disinfectEartag;
    private BigInteger factoryNum;     //工厂号
    private String disinfectTime;     //消毒时间
    private String disinfectName;     //消毒药品名称
    private String disinfectQuality;    //用药剂量
    private String disinfectWay;     //消毒方法
    private String operator;      //操作员(创建表操作人员)
    private String professor;       //技术审核(审核表人员 专家) 可空
    private String supervisor;       //监督员(监督操作员人员) 可空
    private String remark;        //备注
    private String isPass1;       //是否通过审核 可空 默认为 0
    private String unpassReason1;   //未通过审核原因 可空 默认未 无
    private String isPass2;       //是否通过审核 可空 默认为 0
    private String unpassReason2;   //未通过审核原因 可空 默认未 无
    private String gmtCreate;   //创建时间
    private String gmtModified;     //修改时间
    private String gmtProfessor;     //审核时间 可空
    private String gmtSupervise;     //监督确认时间 可空


    //从前台传递的参数
    //目标:json格式
    private String disinfectTimeStart;
    private String disinfectTimeEnd;
    private int page;
    private int size;


    public DisinfectFilesModel() {
    }

    public DisinfectFilesModel(BigInteger factoryNum, String disinfectTime, String disinfectName, String disinfectQuality, String disinfectWay, String operator, String remark, String isPass1,String isPass2, String gmtCreate, String gmtModified) {
        this.factoryNum = factoryNum;
        this.disinfectTime = disinfectTime;
        this.disinfectName = disinfectName;
        this.disinfectQuality = disinfectQuality;
        this.disinfectWay = disinfectWay;
        this.operator = operator;
        this.remark = remark;
        this.isPass1 = isPass1;
        this.isPass2 = isPass2;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    public DisinfectFilesModel(BigInteger factoryNum, String  disinfectTime, String disinfectName, String disinfectQuality, String disinfectWay, String operator, String remark, String isPass1, String isPass2, String gmtCreate) {
        this.factoryNum = factoryNum;
        this.disinfectTime = disinfectTime;
        this.disinfectName = disinfectName;
        this.disinfectQuality = disinfectQuality;
        this.disinfectWay = disinfectWay;
        this.operator = operator;
        this.remark = remark;
        this.isPass1 = isPass1;
        this.isPass2 = isPass2;
        this.gmtCreate = gmtCreate;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisinfectEartag() {
        return disinfectEartag;
    }

    public void setDisinfectEartag(String disinfectEartag) {
        this.disinfectEartag = disinfectEartag;
    }

    public BigInteger getFactoryNum() {
        return factoryNum;
    }

    public void setFactoryNum(BigInteger factoryNum) {
        this.factoryNum = factoryNum;
    }

    public String getDisinfectTime() {
        return disinfectTime;
    }

    public void setDisinfectTime(String disinfectTime) {
        this.disinfectTime = disinfectTime;
    }

    public String getDisinfectName() {
        return disinfectName;
    }

    public void setDisinfectName(String disinfectName) {
        this.disinfectName = disinfectName;
    }

    public String getDisinfectQuality() {
        return disinfectQuality;
    }

    public void setDisinfectQuality(String disinfectQuality) {
        this.disinfectQuality = disinfectQuality;
    }

    public String getDisinfectWay() {
        return disinfectWay;
    }

    public void setDisinfectWay(String disinfectWay) {
        this.disinfectWay = disinfectWay;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIsPass1() {
        return isPass1;
    }

    public void setIsPass1(String isPass1) {
        this.isPass1 = isPass1;
    }

    public String getUnpassReason1() {
        return unpassReason1;
    }

    public void setUnpassReason1(String unpassReason1) {
        this.unpassReason1 = unpassReason1;
    }

    public String getIsPass2() {
        return isPass2;
    }

    public void setIsPass2(String isPass2) {
        this.isPass2 = isPass2;
    }

    public String getUnpassReason2() {
        return unpassReason2;
    }

    public void setUnpassReason2(String unpassReason2) {
        this.unpassReason2 = unpassReason2;
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

    public String getGmtProfessor() {
        return gmtProfessor;
    }

    public void setGmtProfessor(String gmtProfessor) {
        this.gmtProfessor = gmtProfessor;
    }

    public String getGmtSupervise() {
        return gmtSupervise;
    }

    public void setGmtSupervise(String gmtSupervise) {
        this.gmtSupervise = gmtSupervise;
    }

    public String getDisinfectTimeStart() {
        return disinfectTimeStart;
    }

    public void setDisinfectTimeStart(String disinfectTimeStart) {
        this.disinfectTimeStart = disinfectTimeStart;
    }

    public String getDisinfectTimeEnd() {
        return disinfectTimeEnd;
    }

    public void setDisinfectTimeEnd(String disinfectTimeEnd) {
        this.disinfectTimeEnd = disinfectTimeEnd;
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
