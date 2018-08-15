package com.deep.api.response;

public class ClientDetailResponse {
    private String provincialPlatform;
    private Integer factory;

    private Integer provincialTotal;
    private Integer municipaTotal;
    private Integer countryTotal;
    private Integer sheepTotal;
    private Integer sheepNumber;
    private Integer ramTotal;
    private Integer eweTotal;
    private Integer commercialTotal;
    private Integer lambTotal;
    private Integer reserveRamTotal;
    private Integer reserveEweTotal;

    public String getProvincialPlatform() {
        return provincialPlatform;
    }

    public void setProvincialPlatform(String provincialPlatform) {
        this.provincialPlatform = provincialPlatform;
    }

    public Integer getFactory() {
        return factory;
    }

    public void setFactory(Integer factory) {
        this.factory = factory;
    }

    public Integer getProvincialTotal() {
        return provincialTotal;
    }

    public void setProvincialTotal(Integer provincialTotal) {
        this.provincialTotal = provincialTotal;
    }

    public Integer getMunicipaTotal() {
        return municipaTotal;
    }

    public void setMunicipaTotal(Integer municipaTotal) {
        this.municipaTotal = municipaTotal;
    }

    public Integer getCountryTotal() {
        return countryTotal;
    }

    public void setCountryTotal(Integer countryTotal) {
        this.countryTotal = countryTotal;
    }

    public Integer getSheepTotal() {
        return sheepTotal;
    }

    public void setSheepTotal(Integer sheepTotal) {
        this.sheepTotal = sheepTotal;
    }

    public Integer getSheepNumber() {
        return sheepNumber;
    }

    public void setSheepNumber(Integer sheepNumber) {
        this.sheepNumber = sheepNumber;
    }

    public Integer getRamTotal() {
        return ramTotal;
    }

    public void setRamTotal(Integer ramTotal) {
        this.ramTotal = ramTotal;
    }

    public Integer getEweTotal() {
        return eweTotal;
    }

    public void setEweTotal(Integer eweTotal) {
        this.eweTotal = eweTotal;
    }

    public Integer getCommercialTotal() {
        return commercialTotal;
    }

    public void setCommercialTotal(Integer commercialTotal) {
        this.commercialTotal = commercialTotal;
    }

    public Integer getLambTotal() {
        return lambTotal;
    }

    public void setLambTotal(Integer lambTotal) {
        this.lambTotal = lambTotal;
    }

    public Integer getReserveRamTotal() {
        return reserveRamTotal;
    }

    public void setReserveRamTotal(Integer reserveRamTotal) {
        this.reserveRamTotal = reserveRamTotal;
    }

    public Integer getReserveEweTotal() {
        return reserveEweTotal;
    }

    public void setReserveEweTotal(Integer reserveEweTotal) {
        this.reserveEweTotal = reserveEweTotal;
    }

    @Override
    public String toString() {
        return "ClientDetailResponse{" +
                "provincialPlatform='" + provincialPlatform + '\'' +
                ", factory=" + factory +
                ", provincialTotal=" + provincialTotal +
                ", municipaTotal=" + municipaTotal +
                ", countryTotal=" + countryTotal +
                ", sheepTotal=" + sheepTotal +
                ", sheepNumber=" + sheepNumber +
                ", ramTotal=" + ramTotal +
                ", eweTotal=" + eweTotal +
                ", commercialTotal=" + commercialTotal +
                ", lambTotal=" + lambTotal +
                ", reserveRamTotal=" + reserveRamTotal +
                ", reserveEweTotal=" + reserveEweTotal +
                '}';
    }
}
