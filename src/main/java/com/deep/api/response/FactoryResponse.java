package com.deep.api.response;

import java.sql.Timestamp;

public class FactoryResponse {
    private Long id;
    private Timestamp gmtCreate;
    private Timestamp gmtModified;
    private String pkNumber;
    private String breedName;
    private String breedLocation;
    private String breedLocationDetail;
    private Timestamp createTime;
    private Long responsiblePersonId;
    private String responsiblePersonName;

    private String remark;
    private String disinfectP;
    private Short agent;
    private String agentName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPkNumber() {
        return pkNumber;
    }

    public void setPkNumber(String pkNumber) {
        this.pkNumber = pkNumber;
    }

    public String getBreedName() {
        return breedName;
    }

    public void setBreedName(String breedName) {
        this.breedName = breedName;
    }

    public String getBreedLocation() {
        return breedLocation;
    }

    public void setBreedLocation(String breedLocation) {
        this.breedLocation = breedLocation;
    }

    public String getBreedLocationDetail() {
        return breedLocationDetail;
    }

    public void setBreedLocationDetail(String breedLocationDetail) {
        this.breedLocationDetail = breedLocationDetail;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Long getResponsiblePersonId() {
        return responsiblePersonId;
    }

    public void setResponsiblePersonId(Long responsiblePersonId) {
        this.responsiblePersonId = responsiblePersonId;
    }

    public String getResponsiblePersonName() {
        return responsiblePersonName;
    }

    public void setResponsiblePersonName(String responsiblePersonName) {
        this.responsiblePersonName = responsiblePersonName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDisinfectP() {
        return disinfectP;
    }

    public void setDisinfectP(String disinfectP) {
        this.disinfectP = disinfectP;
    }

    public Short getAgent() {
        return agent;
    }

    public void setAgent(Short agent) {
        this.agent = agent;
    }

    public String getAgentName() {
        return agentName;
    }

    public void setAgentName(String agentName) {
        this.agentName = agentName;
    }

    @Override
    public String toString() {
        return "FactoryResponse{" +
                "id=" + id +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", pkNumber='" + pkNumber + '\'' +
                ", breedName='" + breedName + '\'' +
                ", breedLocation='" + breedLocation + '\'' +
                ", breedLocationDetail='" + breedLocationDetail + '\'' +
                ", createTime=" + createTime +
                ", responsiblePersonId=" + responsiblePersonId +
                ", responsiblePersonName='" + responsiblePersonName + '\'' +
                ", remark='" + remark + '\'' +
                ", disinfectP='" + disinfectP + '\'' +
                ", agent=" + agent +
                ", agentName='" + agentName + '\'' +
                '}';
    }
}
