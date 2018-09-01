package com.deep.api.response;

public class SheepInformationResponse {
    private Long id;
    private String trademarkEarTag;
    private String immuneEarTag;
    private Integer building;
    private Integer col;

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

    public String getImmuneEarTag() {
        return immuneEarTag;
    }

    public void setImmuneEarTag(String immuneEarTag) {
        this.immuneEarTag = immuneEarTag;
    }

    public Integer getBuilding() {
        return building;
    }

    public void setBuilding(Integer building) {
        this.building = building;
    }

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }

    @Override
    public String toString() {
        return "SheepInformationResponse{" +
                "id=" + id +
                ", trademarkEarTag='" + trademarkEarTag + '\'' +
                ", immuneEarTag='" + immuneEarTag + '\'' +
                ", building=" + building +
                ", col=" + col +
                '}';
    }
}
