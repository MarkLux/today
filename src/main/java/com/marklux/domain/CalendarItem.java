package com.marklux.domain;

/**
 * Created by mark on 17/11/3.
 */
public class CalendarItem implements Model {
    private long Id;
    private long calendarId;
    private String title;
    private String item;
    private int type;
    private int pickCount;
    private String format;

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public long getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(long calendarId) {
        this.calendarId = calendarId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPickCount() {
        return pickCount;
    }

    public void setPickCount(int pickCount) {
        this.pickCount = pickCount;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
