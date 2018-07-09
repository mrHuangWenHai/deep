package com.deep.api.request;

import javax.validation.constraints.NotBlank;

public class PasswordRequest {
    @NotBlank(message = "不能为空")
    private String oldPassword;
    @NotBlank(message = "不能为空")
    private String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
