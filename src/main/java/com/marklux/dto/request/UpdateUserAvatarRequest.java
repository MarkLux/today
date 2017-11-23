package com.marklux.dto.request;

import javax.validation.constraints.NotNull;

/**
 * Created by mark on 17/11/23.
 */
public class UpdateUserAvatarRequest {
    @NotNull
    private String avatar;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
