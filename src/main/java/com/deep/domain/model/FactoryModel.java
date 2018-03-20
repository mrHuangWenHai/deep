package com.deep.domain.model;

import java.sql.Timestamp;

public class FactoryModel {
    private long id;
    private Timestamp gmtCreate;
    private Timestamp gmtModified;
    private String pkNumber;
    private String breadName;
    private String breadLocation;
    private Timestamp createTime;
    private long responsiblePersonid;
    private String remark;
    private String disnfectP;
    private short agent;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getBreadName() {
        return breadName;
    }

    public void setBreadName(String breadName) {
        this.breadName = breadName;
    }

    public String getBreadLocation() {
        return breadLocation;
    }

    public void setBreadLocation(String breadLocation) {
        this.breadLocation = breadLocation;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public long getResponsiblePersonid() {
        return responsiblePersonid;
    }

    public void setResponsiblePersonid(long responsiblePersonid) {
        this.responsiblePersonid = responsiblePersonid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDisnfectP() {
        return disnfectP;
    }

    public void setDisnfectP(String disnfectP) {
        this.disnfectP = disnfectP;
    }

    public short getAgent() {
        return agent;
    }

    public void setAgent(short agent) {
        this.agent = agent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FactoryModel that = (FactoryModel) o;

        if (id != that.id) return false;
        if (responsiblePersonid != that.responsiblePersonid) return false;
        if (agent != that.agent) return false;
        if (gmtCreate != null ? !gmtCreate.equals(that.gmtCreate) : that.gmtCreate != null) return false;
        if (gmtModified != null ? !gmtModified.equals(that.gmtModified) : that.gmtModified != null) return false;
        if (pkNumber != null ? !pkNumber.equals(that.pkNumber) : that.pkNumber != null) return false;
        if (breadName != null ? !breadName.equals(that.breadName) : that.breadName != null) return false;
        if (breadLocation != null ? !breadLocation.equals(that.breadLocation) : that.breadLocation != null)
            return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (disnfectP != null ? !disnfectP.equals(that.disnfectP) : that.disnfectP != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (gmtCreate != null ? gmtCreate.hashCode() : 0);
        result = 31 * result + (gmtModified != null ? gmtModified.hashCode() : 0);
        result = 31 * result + (pkNumber != null ? pkNumber.hashCode() : 0);
        result = 31 * result + (breadName != null ? breadName.hashCode() : 0);
        result = 31 * result + (breadLocation != null ? breadLocation.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (int) (responsiblePersonid ^ (responsiblePersonid >>> 32));
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (disnfectP != null ? disnfectP.hashCode() : 0);
        result = 31 * result + (int) agent;
        return result;
    }
}
