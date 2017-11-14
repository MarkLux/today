package com.marklux.domain;

import javax.validation.constraints.NotNull;

/**
 * Created by mark on 17/11/3.
 */
public class CalendarSubscribe implements Model {
    @NotNull
    private long calendarId;
    private long userId;
    @NotNull
    private int order;

    public long getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(long calendarId) {
        this.calendarId = calendarId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
