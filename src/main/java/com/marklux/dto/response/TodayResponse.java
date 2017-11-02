package com.marklux.dto.response;

import com.marklux.domain.CalendarActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mark on 17/11/2.
 */
public class TodayResponse {

    private String calendarName;
    private long calendarId;
    private List<Activity> good;
    private List<Activity> bad;
    private List<Recommend> recommend;

    public TodayResponse() {
        this.good = new ArrayList<>();
        this.bad = new ArrayList<>();
        this.recommend = new ArrayList<>();
    }

    public String getCalendarName() {
        return calendarName;
    }

    public void setCalendarName(String calendarName) {
        this.calendarName = calendarName;
    }

    public long getCalendarId() {
        return calendarId;
    }

    public void setCalendarId(long calendarId) {
        this.calendarId = calendarId;
    }

    public List<Activity> getGood() {
        return good;
    }

    public void setGood(List<Activity> good) {
        this.good = good;
    }

    public List<Activity> getBad() {
        return bad;
    }

    public void setBad(List<Activity> bad) {
        this.bad = bad;
    }

    public void addActivity(CalendarActivity activity,boolean bad) {
        if (bad) {
            this.bad.add(new Activity(activity.getName(),activity.getBad()));
        }
        else {
            this.good.add(new Activity(activity.getName(),activity.getGood()));
        }
    }

    public class Activity {
        private String title;
        private String description;

        public Activity(String title, String description) {
            this.title = title;
            this.description = description;
        }

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
    }

    public class Recommend {
        private String name;
        private List<String> items;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getItems() {
            return items;
        }

        public void setItems(List<String> items) {
            this.items = items;
        }
    }
}
