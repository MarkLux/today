package com.marklux.domain;

/**
 * Created by mark on 17/11/2.
 */
public class CalendarActivity implements Model {
    private long id;
    private long calendarId;
    private String name;
    private String good;
    private String bad;
    private int weekendOnly;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(long calendarId) {
        this.calendarId = calendarId;
    }

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
