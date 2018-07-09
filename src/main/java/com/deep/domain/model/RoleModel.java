package com.deep.domain.model;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

public class RoleModel {

    private long id;
    private Timestamp gmtCreate;
    private Timestamp gmtModified;

    private String pkTypeid;

    @NotBlank(message = "角色名称不能为空")
    private String typeName;

    @NotBlank(message = "角色描述不能为空")
    private String roleDescription;

    private String defaultPermit;

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public String getPkTypeid() {
        return pkTypeid;
    }

    public void setPkTypeid(String pkTypeid) {
        this.pkTypeid = pkTypeid;
    }

    public String getDefaultPermit() {
        return defaultPermit;
    }

    public void setDefaultPermit(String defaultPermit) {
        this.defaultPermit = defaultPermit;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleModel that = (RoleModel) o;

        if (id != that.id) return false;
        if (pkTypeid != that.pkTypeid) return false;
        if (defaultPermit != that.defaultPermit) return false;
        if (gmtCreate != null ? !gmtCreate.equals(that.gmtCreate) : that.gmtCreate != null) return false;
        if (gmtModified != null ? !gmtModified.equals(that.gmtModified) : that.gmtModified != null) return false;
        if (typeName != null ? !typeName.equals(that.typeName) : that.typeName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (gmtCreate != null ? gmtCreate.hashCode() : 0);
        result = 31 * result + (gmtModified != null ? gmtModified.hashCode() : 0);
        result = 31 * result + (typeName != null ? typeName.hashCode() : 0);
        result = 31 * result + (pkTypeid != null ? pkTypeid.hashCode() : 0);
        result = 31 * result + (defaultPermit != null ? defaultPermit.hashCode() : 0);
        return result;

    }
}
