package com.deep.api.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

public class FactoryRequest {
    // 报废
    private Short agent;

    private String pkNumber;
    @NotBlank(message = "不能为空")
    private String breedName;
    @NotBlank(message = "不能为空")
    private String breedLocation;
    @NotBlank(message = "不能为空")
    private String breedLocationDetail;
    private Timestamp createTime;
    @Min(value = -1, message = "负责人错误")
    private Long responsibleId;
    // 羊场负责人
    private String responsiblePersonId;

    private String remark;
//    @NotBlank(message = "不能为空")
    private String disinfectP;
    @Min(value = -1, message = "代理号错误")
    private Short factoryNum;


    public Short getAgent() {
        return agent;
    }

    public void setAgent(Short agent) {
        this.agent = agent;
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

    public Long getResponsibleId() {
        return responsibleId;
    }

    public void setResponsibleId(Long responsibleId) {
        this.responsibleId = responsibleId;
    }

    public String getResponsiblePersonId() {
        return responsiblePersonId;
    }

    public void setResponsiblePersonId(String responsiblePersonId) {
        this.responsiblePersonId = responsiblePersonId;
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

    public Short getFactoryNum() {
        return factoryNum;
    }

    public void setFactoryNum(Short factoryNum) {
        this.factoryNum = factoryNum;
    }
}
