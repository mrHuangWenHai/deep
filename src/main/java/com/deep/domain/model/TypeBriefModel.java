package com.deep.domain.model;

import javax.validation.constraints.NotEmpty;

/**
 * create by zhongrui on 18-4-16.
 */
public class TypeBriefModel {
    private long id;
    @NotEmpty
    private String type;
    @NotEmpty
    private String brief;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }
}
