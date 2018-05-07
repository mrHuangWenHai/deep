package com.deep.domain.model;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

public class FactoryModel {
    private Long id;
    private Timestamp gmtCreate;
    private Timestamp gmtModified;
    @NotBlank(message = "羊场号码不能为空")
    private String pkNumber;
    @NotBlank(message = "不能为空")
    private String breedName;
    @NotBlank(message = "不能为空")
    private String breedLocation;
    @NotBlank(message = "不能为空")
    private String breedLocationDetail;
    private Timestamp createTime;
    @Min(value = -1, message = "负责人错误")
    private Long responsiblePersonId;
    private String responsiblePersonName;

    private String remark;
    @NotBlank(message = "不能为空")
    private String disinfectP;
    @Min(value = -1, message = "代理号错误")
    private Short agent;

    public String getBreedLocationDetail() {
        return breedLocationDetail;
    }

    public void setBreedLocationDetail(String breedLocationDetail) {
        this.breedLocationDetail = breedLocationDetail;
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

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
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

    public String getResponsiblePersonName() {
        return responsiblePersonName;
    }

    public void setResponsiblePersonName(String responsiblePersonName) {
        this.responsiblePersonName = responsiblePersonName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getResponsiblePersonId() {
        return responsiblePersonId;
    }

    public void setResponsiblePersonId(Long responsiblePersonId) {
        this.responsiblePersonId = responsiblePersonId;
    }

    public Short getAgent() {
        return agent;
    }

    public void setAgent(Short agent) {
        this.agent = agent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FactoryModel that = (FactoryModel) o;

        if (id != that.id) return false;

        if (responsiblePersonId != that.responsiblePersonId) return false;

        if (agent != that.agent) return false;
        if (gmtCreate != null ? !gmtCreate.equals(that.gmtCreate) : that.gmtCreate != null) return false;
        if (gmtModified != null ? !gmtModified.equals(that.gmtModified) : that.gmtModified != null) return false;
        if (pkNumber != null ? !pkNumber.equals(that.pkNumber) : that.pkNumber != null) return false;

        if (breedName != null ? !breedName.equals(that.breedName) : that.breedName != null) return false;
        if (breedLocation != null ? !breedLocation.equals(that.breedLocation) : that.breedLocation != null)
            return false;
        if (createTime != null ? !createTime.equals(that.createTime) : that.createTime != null) return false;
        if (remark != null ? !remark.equals(that.remark) : that.remark != null) return false;
        if (disinfectP != null ? !disinfectP.equals(that.disinfectP) : that.disinfectP != null) return false;


        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (gmtCreate != null ? gmtCreate.hashCode() : 0);
        result = 31 * result + (gmtModified != null ? gmtModified.hashCode() : 0);
        result = 31 * result + (pkNumber != null ? pkNumber.hashCode() : 0);

        result = 31 * result + (breedName != null ? breedName.hashCode() : 0);
        result = 31 * result + (breedLocation != null ? breedLocation.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (int) (responsiblePersonId ^ (responsiblePersonId >>> 32));
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (disinfectP != null ? disinfectP.hashCode() : 0);
        result = 31 * result + (int) agent;
        return result;
    }

    @Override
    public String toString() {
        return "FactoryModel{" +
                "id=" + id +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", pkNumber='" + pkNumber + '\'' +
                ", breedName='" + breedName + '\'' +
                ", breedLocation='" + breedLocation + '\'' +
                ", createTime=" + createTime +
                ", responsiblePersonId=" + responsiblePersonId +
                ", remark='" + remark + '\'' +
                ", disinfectP='" + disinfectP + '\'' +
                ", agent=" + agent +
                '}';
    }
}
