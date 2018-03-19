package com.deep.domain.model;

import java.sql.Timestamp;

public class RoleModel {
    private long id;
    private String authority;
    private Timestamp gmtCreate;
    private Timestamp gmtModified;

    public RoleModel() {
    }

    public RoleModel(long id, String authority, Timestamp gmtCreate, Timestamp gmtModified) {
        this.id = id;
        this.authority = authority;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Timestamp getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Timestamp gmtModified) {
        this.gmtModified = gmtModified;
    }
}
