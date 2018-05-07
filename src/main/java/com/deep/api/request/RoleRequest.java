package com.deep.api.request;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class RoleRequest {
    private Long id;

    @NotBlank(message = "角色名称不能为空")
    private String typeName;

    @NotBlank(message = "角色描述不能为空")
    private String roleDescription;

    private List<String> rolePermit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getRolePermit() {
        return rolePermit;
    }

    public void setRolePermit(List<String> rolePermit) {
        this.rolePermit = rolePermit;
    }

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
}
