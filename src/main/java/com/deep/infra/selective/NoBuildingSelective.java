package com.deep.infra.selective;

public class NoBuildingSelective {
    public String setBuildingAndCol(String sheeps, Long buildingCol, Long factory) {
        String sql = "update sheep_information set ";
        String bc = "building_column = " + buildingCol + " ";
        String where = "where factory = " + factory + " ";
        String id = " and id in (";
        String old = sheeps.toString();
        String value = old.substring(1, old.length()-1);
        sql += bc + where + id + value + ")";
        System.out.println("sql = " + sql);
        return sql;
    }
}
