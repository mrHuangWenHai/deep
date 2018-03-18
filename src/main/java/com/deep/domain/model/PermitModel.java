package com.deep.domain.model;

import com.fasterxml.jackson.annotation.JsonView;

import java.sql.Timestamp;

public class PermitModel {
    public interface PermitSimpleView {};
    public interface PermitDetailView extends PermitSimpleView {};

    private long id;
    private Timestamp gmtCreate;
    private Timestamp gmtModified;
    private long permitId;
    private String permitName;

    @JsonView(PermitDetailView.class)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @JsonView(PermitDetailView.class)
    public Timestamp getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Timestamp gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    @JsonView(PermitDetailView.class)
    public Timestamp getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Timestamp gmtModified) {
        this.gmtModified = gmtModified;
    }

    @JsonView(PermitSimpleView.class)
    public long getPermitId() {
        return permitId;
    }

    public void setPermitId(long permitId) {
        this.permitId = permitId;
    }

    @JsonView(PermitSimpleView.class)
    public String getPermitName() {
        return permitName;
    }

    public void setPermitName(String permitName) {
        this.permitName = permitName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PermitModel that = (PermitModel) o;

        if (id != that.id) return false;
        if (permitId != that.permitId) return false;
        if (gmtCreate != null ? !gmtCreate.equals(that.gmtCreate) : that.gmtCreate != null) return false;
        if (gmtModified != null ? !gmtModified.equals(that.gmtModified) : that.gmtModified != null) return false;
        if (permitName != null ? !permitName.equals(that.permitName) : that.permitName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (gmtCreate != null ? gmtCreate.hashCode() : 0);
        result = 31 * result + (gmtModified != null ? gmtModified.hashCode() : 0);
        result = 31 * result + (int) (permitId ^ (permitId >>> 32));
        result = 31 * result + (permitName != null ? permitName.hashCode() : 0);
        return result;
    }
}
