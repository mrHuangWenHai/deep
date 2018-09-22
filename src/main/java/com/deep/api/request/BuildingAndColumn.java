package com.deep.api.request;

public class BuildingAndColumn {
    private Integer building;
    private Integer column;

    public Integer getBuilding() {
        return building;
    }

    public void setBuilding(Integer building) {
        this.building = building;
    }

    public Integer getColumn() {
        return column;
    }

    public void setColumn(Integer column) {
        this.column = column;
    }

    public BuildingAndColumn(Integer building, Integer column) {
        this.building = building;
        this.column = column;
    }

    public BuildingAndColumn() {

    }

    @Override
    public String toString() {
        return "BuildingAndColumn{" +
                "building=" + building +
                ", column=" + column +
                '}';
    }
}
