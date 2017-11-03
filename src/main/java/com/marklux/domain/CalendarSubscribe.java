package com.marklux.domain;

/**
 * Created by mark on 17/11/3.
 */
public class CalendarSubscribe {
    private long calendarId;
    private long userId;

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
}
