package com.marklux.mapper;

import com.marklux.domain.CalendarActivity;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * Created by mark on 17/11/2.
 */
public interface CalendarActivityMapper {
    Collection<CalendarActivity> getActivitiesByCalendarId(long calendarId);
    int createActivities(List<CalendarActivity> list);
    void createActivity(CalendarActivity calendarActivity);
    int updateActivity(CalendarActivity calendarActivity);
    int deleteActivity(long activityId);
    int deleteActivitiesByCalendarId(@Param("calendarId") long calendarId);
    CalendarActivity getActivity(long activityId);
}
