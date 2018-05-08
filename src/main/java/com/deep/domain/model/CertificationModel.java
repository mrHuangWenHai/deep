package com.deep.domain.model;

import java.io.Serializable;

public class CertificationModel implements Serializable {
    private Integer id;

    private Integer plantId;

    private String plantName;

    private String certificateAddress;

    private Integer returnId;

    private Integer pageNumb = 1;

    public Integer getReturnId() {
        return returnId;
    }

    public void setReturnId(Integer returnId) {
        this.returnId = returnId;
    }

    public Integer getPageNumb() {
        return pageNumb;
    }

    public void setPageNumb(Integer pageNumb) {
        this.pageNumb = pageNumb;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    private Integer limit = 10;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPlantId() {
        return plantId;
    }

    public void setPlantId(Integer plantId) {
        this.plantId = plantId;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName == null ? null : plantName.trim();
    }

    public String getCertificateAddress() {
        return certificateAddress;
    }

    public void setCertificateAddress(String certificateAddress) {
        this.certificateAddress = certificateAddress == null ? null : certificateAddress.trim();
    }

    @Override
    public String toString(){
        return "CertificationModel{"+
                "id=" + id +
                ", plantId='" + plantId + '\'' +
                ", plantName='" + plantName + '\'' +
                ", certificateAddress='" + certificateAddress + '\'' +
                ", pageNumb=" + pageNumb +
                ", limit=" + limit +
                '}';
    }
}