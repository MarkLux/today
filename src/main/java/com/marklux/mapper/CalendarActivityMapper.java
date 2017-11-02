package com.marklux.mapper;

import com.marklux.domain.CalendarActivity;

import java.util.Collection;

/**
 * Created by mark on 17/11/2.
 */
public interface CalendarActivityMapper {
    Collection<CalendarActivity> getActivitiesByCalendarId(long calendarId);
    void createActivity(CalendarActivity calendarActivity);
    int updateActitvity(CalendarActivity calendarActivity);
    int delteActivity(long activityId);
    CalendarActivity getActivity(long activityId);
}
