package com.deep.api.request;

import java.util.List;

public class TradeMarkEarTagRequest {
    private Long factory;
    private List<BuildingAndColumn> buildings;

    public Long getFactory() {
        return factory;
    }

    public void setFactory(Long factory) {
        this.factory = factory;
    }

    public List<BuildingAndColumn> getBuildings() {
        return buildings;
    }

    public void setBuildings(List<BuildingAndColumn> buildings) {
        this.buildings = buildings;
    }

    @Override
    public String toString() {
        return "TradeMarkEarTagRequest{" +
                "factory=" + factory +
                ", buildings=" + buildings.toString() +
                '}';
    }
}