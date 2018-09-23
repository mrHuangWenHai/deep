package com.deep.domain.model.sheepInfo;

import javax.validation.constraints.NotNull;

public class BuildingColumnModel {
    private Long id;
    @NotNull
    private Long factory;
    @NotNull
    private Integer building;
    @NotNull
    private Integer col;

    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFactory() {
        return factory;
    }

    public void setFactory(Long factory) {
        this.factory = factory;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "BuildingColumnModel{" +
                "id=" + id +
                ", factory=" + factory +
                ", building=" + building +
                ", col=" + col +
                ", type='" + type + '\'' +
                '}';
    }
}
