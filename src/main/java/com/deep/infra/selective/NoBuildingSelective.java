package com.deep.infra.selective;

import java.util.List;

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

    public String getSheepEarTag(Long factory, List<Long> buildingColumn) {
        String sql = "select trademark_ear_tag from sheep_information ";
        String factoryIdea = "where factory = " + factory + " ";
        String buildingColumnString = buildingColumn.toString();
        String buildingColumnIdea = "and building_column in (" + buildingColumnString.substring(1, buildingColumnString.length()-1) + ")";
        sql = sql + factoryIdea + buildingColumnIdea;
        System.out.println(sql);
        return sql;
    }

    public String getSheepImmuneTag(Long factory, List<Long> buildingColumn) {
        String sql = "select immune_ear_tag from sheep_information ";
        String factoryIdea = "where factory = " + factory + " ";
        String buildingColumnString = buildingColumn.toString();
        String buildingColumnIdea = "and building_column in (" + buildingColumnString.substring(1, buildingColumnString.length()-1) + ")";
        sql = sql + factoryIdea + buildingColumnIdea;
        System.out.println(sql);
        return sql;
    }
}
