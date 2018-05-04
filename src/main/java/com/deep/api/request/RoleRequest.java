package com.deep.api.request;

import javax.validation.constraints.NotBlank;

public class RoleRequest {
    @NotBlank(message = "角色名称不能为空")
    private String typeName;

    @NotBlank(message = "角色描述不能为空")
    private String roleDescription;

    private String[] defaultPermit;

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public String[] getDefaultPermit() {
        return defaultPermit;
    }

    public void setDefaultPermit(String[] defaultPermit) {
        this.defaultPermit = defaultPermit;
    }
}
