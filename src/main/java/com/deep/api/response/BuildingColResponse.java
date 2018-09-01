package com.deep.api.response;

public class BuildingColResponse {
    private Long id;
    private Integer building;
    private Integer col;
    private Long number;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "BuildingColResponse{" +
                "id=" + id +
                ", building=" + building +
                ", col=" + col +
                ", number=" + number +
                '}';
    }
}
