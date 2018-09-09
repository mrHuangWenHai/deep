package com.deep.api.response;

public class NoBuildingColResponse {
    private Long id;
    private String trademarkEarTag;
    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrademarkEarTag() {
        return trademarkEarTag;
    }

    public void setTrademarkEarTag(String trademarkEarTag) {
        this.trademarkEarTag = trademarkEarTag;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "NoBuildingColResponse{" +
                "id=" + id +
                ", trademarkEarTag='" + trademarkEarTag + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
