package com.deep.api.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class BCRequest {
    @NotNull
    private Long factory;
    @NotNull
    private Integer building;
    @NotNull
    private Integer colNum;

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

    public Integer getColNum() {
        return colNum;
    }

    public void setColNum(Integer colNum) {
        this.colNum = colNum;
    }

    @Override
    public String toString() {
        return "BCRequest{" +
                "factory=" + factory +
                ", building=" + building +
                ", colNum=" + colNum +
                '}';
    }
}
