package com.deep.api.response;

public class ClientDetailResponse {
    private String provincialPlatform;// 平台名称
    private Long factory;             // 编号
    private Byte rank;                // 等级

    private Integer provincialTotal; // 省级代理数目
    private Integer municipaTotal;   // 市级代理数目
    private Integer countryTotal;    // 县级代理数目
    private Integer sheepTotal;      // 羊场数目

    private Long sheepNumber;     // 羊场总数

    private Long ramTotal;        // 种公羊
    private Long eweTotal;        // 种母羊
    private Long commercialTotal; // 商品羊
    private Long lambTotal;       // 羔羊
    private Long reserveRamTotal; // 后备种公羊
    private Long reserveEweTotal; // 后背种母羊

    public ClientDetailResponse(String provincialPlatform, Long factory, Byte rank, Integer provincialTotal, Integer municipaTotal, Integer countryTotal, Integer sheepTotal, Long sheepNumber, Long ramTotal, Long eweTotal, Long commercialTotal, Long lambTotal, Long reserveRamTotal, Long reserveEweTotal) {
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

    public Long getSheepNumber() {
        return sheepNumber;
    }

    public void setSheepNumber(Long sheepNumber) {
        this.sheepNumber = sheepNumber;
    }

    public Long getRamTotal() {
        return ramTotal;
    }

    public void setRamTotal(Long ramTotal) {
        this.ramTotal = ramTotal;
    }

    public Long getEweTotal() {
        return eweTotal;
    }

    public void setEweTotal(Long eweTotal) {
        this.eweTotal = eweTotal;
    }

    public Long getCommercialTotal() {
        return commercialTotal;
    }

    public void setCommercialTotal(Long commercialTotal) {
        this.commercialTotal = commercialTotal;
    }

    public Long getLambTotal() {
        return lambTotal;
    }

    public void setLambTotal(Long lambTotal) {
        this.lambTotal = lambTotal;
    }

    public Long getReserveRamTotal() {
        return reserveRamTotal;
    }

    public void setReserveRamTotal(Long reserveRamTotal) {
        this.reserveRamTotal = reserveRamTotal;
    }

    public Long getReserveEweTotal() {
        return reserveEweTotal;
    }

    public void setReserveEweTotal(Long reserveEweTotal) {
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
