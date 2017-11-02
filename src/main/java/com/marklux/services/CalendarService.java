package com.marklux.services;

import com.marklux.domain.Calendar;
import com.marklux.domain.CalendarActivity;
import com.marklux.dto.response.TodayResponse;
import com.marklux.exception.BaseException;
import com.marklux.exception.general.ResourceNotExistException;
import com.marklux.mapper.CalendarActivityMapper;
import com.marklux.mapper.CalendarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by mark on 17/10/31.
 */
@Service
public class CalendarService {
    @Autowired
    private CalendarMapper calendarMapper;
    @Autowired
    private CalendarActivityMapper calendarActivityMapper;

    public long createCalendar(Calendar calendar) {
        calendarMapper.createCalendar(calendar);
        return calendar.getId();
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

    public TodayResponse getToday(long calendarId) throws BaseException {
        // 构建随机池
        Calendar calendar = calendarMapper.getCalendar(calendarId);
        if (calendar == null) {
            throw new ResourceNotExistException("日历");
        }

        Collection<CalendarActivity> activities = calendarActivityMapper.getActivitiesByCalendarId(calendarId);
        System.out.println(activities);
        List<CalendarActivity> picked = pickRandomActivities(activities, calendar.getBadPick() + calendar.getGoodPick());

        TodayResponse response = new TodayResponse();

        response.setCalendarName(calendar.getTitle());
        response.setCalendarId(calendarId);
        for (int i = 0; i < calendar.getGoodPick(); i++) {
            if (picked.get(i) == null) {
                continue;
            }
            response.addActivity(picked.get(i),false);
        }
        for (int i=calendar.getGoodPick();i<picked.size();i++) {
            if (picked.get(i) == null) {
                continue;
            }
            response.addActivity(picked.get(i),true);
        }

        return response;
    }

    private List<CalendarActivity> pickRandomActivities(Collection<CalendarActivity> activities, int num) {
        List<CalendarActivity> list = new ArrayList<>(activities);
        for (int i=num;i<list.size();i++) {
            list.remove(i);
        }
        return list;
    }
}