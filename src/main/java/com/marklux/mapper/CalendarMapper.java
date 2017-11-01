package com.marklux.mapper;

import com.marklux.domain.Calendar;
import java.util.Collection;

/**
 * Created by mark on 17/10/31.
 */

public interface CalendarMapper {
    Calendar getCalendar(long Id);
    Collection<Calendar> getCalendarBy(String key,String value);

    void createCalendar(Calendar calendar);

    int updateCalendar(Calendar calendar);

    int deleteCalendar(long Id);
}
