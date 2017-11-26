package com.marklux.domain;

import com.marklux.dto.response.TodayResponse;

/**
 * Created by mark on 17/11/5.
 */
public class CalendarDetail implements Model {
    private long id;
    private String title;
    private String description;
    private long creatorId;
    private String creatorName;
    private String creatorAvatar;
    private int subscribed;
    private String picture;
    private long createdAt;
    private long updatedAt;
    private int isPublic;
    private String password;
    private int goodPick;
    private int badPick;
    private boolean isSubscribed;
    private TodayResponse preview;

    public boolean getIsSubscribed() {
        return isSubscribed;
    }

    public void setIsSubscribed(boolean subscribed) {
        isSubscribed = subscribed;
    }



    public TodayResponse getPreview() {
        return preview;
    }

    public void setPreview(TodayResponse preview) {
        this.preview = preview;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getCreatorAvatar() {
        return creatorAvatar;
    }

    public void setCreatorAvatar(String creatorAvatar) {
        this.creatorAvatar = creatorAvatar;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(long creatorId) {
        this.creatorId = creatorId;
    }

    public int getSubscribed() {
        return subscribed;
    }

    public void setSubscribed(int subscribed) {
        this.subscribed = subscribed;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
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
