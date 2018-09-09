package com.deep.api.request;

import javax.validation.constraints.NotNull;
import java.util.List;

public class NoBuildingRequest {
    @NotNull(message = "羊场号不能为空")
    private Long factory;
    @NotNull(message = "必须要选择羊只")
    private List<Long> sheeps;
    @NotNull(message = "栋号不能为空")
    private Integer building;
    @NotNull(message = "栏号不能为空")
    private Integer col;

    public Long getFactory() {
        return factory;
    }

    public void setFactory(Long factory) {
        this.factory = factory;
    }

    public List<Long> getSheeps() {
        return sheeps;
    }

    public void setSheeps(List<Long> sheeps) {
        this.sheeps = sheeps;
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
        return "NoBuildingRequest{" +
                "factory=" + factory +
                ", sheeps=" + sheeps.toString() +
                ", building=" + building +
                ", col=" + col +
                '}';
    }
}
