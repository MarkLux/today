package com.marklux.domain;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mark on 17/11/3.
 */
public class CalendarItem implements Model {
    private long id;
    private long calendarId;
    @NotBlank
    private String name;
    @NotBlank
    private String item;

    private List<String> items; // 用于提前解析

    private int type;
    @NotNull
    @Min(1)

    private int pickCount;
    private String format;

    public List<String> getItems() {
        if (this.items == null) {
            String [] splits = this.item.split(" ");
            this.items = new ArrayList<String>();
            for (int i=0;i<splits.length;i++) {
                this.items.add(splits[i]);
            }
        }
        return this.items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

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
