package com.marklux.dto.request;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by mark on 17/11/6.
 */

public class CreateActivitiesRequest {
    @NotBlank
    private String name;

    private String good;

    private String bad;

    private int weekendOnly;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGood() {
        return good;
    }

    public void setGood(String good) {
        this.good = good;
    }

    public String getBad() {
        return bad;
    }

    public void setBad(String bad) {
        this.bad = bad;
    }

    public int getWeekendOnly() {
        return weekendOnly;
    }

    public void setWeekendOnly(int weekendOnly) {
        this.weekendOnly = weekendOnly;
    }
}
