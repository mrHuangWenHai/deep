package com.deep.api.request;

import javax.validation.constraints.NotNull;

public class RolePermitRequest {
    private Long id;

    @NotNull(message = "role不能为空")
    private Long role;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
        this.role = role;
    }
}
