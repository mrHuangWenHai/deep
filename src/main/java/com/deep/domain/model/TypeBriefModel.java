package com.deep.domain.model;

import javax.validation.constraints.NotEmpty;

/**
 * create by zhongrui on 18-4-16.
 */
public class TypeBriefModel {

    @NotEmpty
    private String typeName;
    @NotEmpty
    private String brief;

    public String getTypename() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }
}
