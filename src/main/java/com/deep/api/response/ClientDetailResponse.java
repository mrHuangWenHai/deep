package com.deep.api.response;

public class ClientDetailResponse {
    private String provincialPlatform;// 平台名称
    private Long factory;             // 编号
    private Byte rank;                // 等级

    private Integer provincialTotal; // 省级代理数目
    private Integer municipaTotal;   // 市级代理数目
    private Integer countryTotal;    // 县级代理数目
    private Integer sheepTotal;      // 羊场数目

    private Integer sheepNumber;     // 羊场总数

    private Integer ramTotal;        // 种公羊
    private Integer eweTotal;        // 种母羊
    private Integer commercialTotal; // 商品羊
    private Integer lambTotal;       // 羔羊
    private Integer reserveRamTotal; // 后备种公羊
    private Integer reserveEweTotal; // 后背种母羊

    public ClientDetailResponse(String provincialPlatform, Long factory, Byte rank, Integer provincialTotal, Integer municipaTotal, Integer countryTotal, Integer sheepTotal, Integer sheepNumber, Integer ramTotal, Integer eweTotal, Integer commercialTotal, Integer lambTotal, Integer reserveRamTotal, Integer reserveEweTotal) {
        this.provincialPlatform = provincialPlatform;
        this.factory = factory;
        this.rank = rank;
        this.provincialTotal = provincialTotal;
        this.municipaTotal = municipaTotal;
        this.countryTotal = countryTotal;
        this.sheepTotal = sheepTotal;
        this.sheepNumber = sheepNumber;
        this.ramTotal = ramTotal;
        this.eweTotal = eweTotal;
        this.commercialTotal = commercialTotal;
        this.lambTotal = lambTotal;
        this.reserveRamTotal = reserveRamTotal;
        this.reserveEweTotal = reserveEweTotal;
    }

    public ClientDetailResponse() {

    }

    public Byte getRank() {
        return rank;
    }

    public void setRank(Byte rank) {
        this.rank = rank;
    }

    public String getProvincialPlatform() {
        return provincialPlatform;
    }

    public void setProvincialPlatform(String provincialPlatform) {
        this.provincialPlatform = provincialPlatform;
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


    public void setFactory(Long factory) {
        this.factory = factory;
    }

    public Long getFactory() {
        return factory;
    }

    @Override
    public String toString() {
        return "ClientDetailResponse{" +
                "provincialPlatform='" + provincialPlatform + '\'' +
                ", factory=" + factory +
                ", rank=" + rank +
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
