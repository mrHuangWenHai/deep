package com.deep.api.request;

public class SheepUpdateRequest {
    private Long factory;
    private Long sheepid;
    private String tradeMarkEartag;
    private String breedingSheepBase;

    public Long getFactory() {
        return factory;
    }

    public void setFactory(Long factory) {
        this.factory = factory;
    }

    public Long getSheepid() {
        return sheepid;
    }

    public void setSheepid(Long sheepid) {
        this.sheepid = sheepid;
    }

    public String getTradeMarkEartag() {
        return tradeMarkEartag;
    }

    public void setTradeMarkEartag(String tradeMarkEartag) {
        this.tradeMarkEartag = tradeMarkEartag;
    }

    public String getBreedingSheepBase() {
        return breedingSheepBase;
    }

    public void setBreedingSheepBase(String breedingSheepBase) {
        this.breedingSheepBase = breedingSheepBase;
    }

    @Override
    public String toString() {
        return "SheepUpdateRequest{" +
                "factory=" + factory +
                ", sheepid=" + sheepid +
                ", tradeMarkEartag='" + tradeMarkEartag + '\'' +
                ", breedingSheepBase='" + breedingSheepBase + '\'' +
                '}';
    }
}
