package com.marklux.dto.request;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by mark on 17/10/31.
 */
public class CreateCalendarRequest {
    @NotBlank
    private String title;
    private String description;
    @NotNull
    private int isPublic;
    private String password;
    @NotNull
    private int goodPick;
    @NotNull
    private int badPick;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(int isPublic) {
        this.isPublic = isPublic;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getGoodPick() {
        return goodPick;
    }

    public void setGoodPick(int goodPick) {
        this.goodPick = goodPick;
    }

    public int getBadPick() {
        return badPick;
    }

    public void setBadPick(int badPick) {
        this.badPick = badPick;
    }
}
