package com.deep.domain.model;

import java.util.Map;

public class ExtendModel {
    private String tableName;
    private Map<String, String> columns;

    public ExtendModel() {
    }

    public ExtendModel(String tableName, Map<String, String> columns) {
        this.tableName = tableName;
        this.columns = columns;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Map<String, String> getColumns() {
        return columns;
    }

    public void setColumns(Map<String, String> columns) {
        this.columns = columns;
    }
}
