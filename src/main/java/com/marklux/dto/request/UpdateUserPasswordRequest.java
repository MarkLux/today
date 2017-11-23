package com.marklux.dto.request;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by mark on 17/11/23.
 */
public class UpdateUserPasswordRequest {
    @NotBlank
    private String oldPassword;
    @NotBlank
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
