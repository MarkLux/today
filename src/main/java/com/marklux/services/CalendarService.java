package com.marklux.services;

import com.marklux.domain.Calendar;
import com.marklux.mappers.CalendarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mark on 17/10/31.
 */
@Service
public class CalendarService {
    @Autowired
    private CalendarMapper calendarMapper;

    public long createCalendar(Calendar calendar) {
        calendarMapper.createCalendar(calendar);
        return  calendar.getId();
    }

    public Calendar getCalendar(long Id) {
        return calendarMapper.getCalendar(Id);
    }

    public boolean updateCalendar(Calendar calendar) {
        return calendarMapper.updateCalendar(calendar) == 1;
    }

    public boolean deleteCalendar(long Id) {
        return calendarMapper.deleteCalendar(Id) == 1;
    }
}
