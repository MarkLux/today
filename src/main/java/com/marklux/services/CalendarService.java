package com.marklux.services;

import com.marklux.common.Utils;
import com.marklux.domain.Calendar;
import com.marklux.domain.CalendarActivity;
import com.marklux.domain.CalendarItem;
import com.marklux.domain.CalendarSubscribe;
import com.marklux.dto.response.TodayResponse;
import com.marklux.exception.BaseException;
import com.marklux.exception.general.ResourceNotExistException;
import com.marklux.mapper.CalendarActivityMapper;
import com.marklux.mapper.CalendarItemMapper;
import com.marklux.mapper.CalendarMapper;
import com.marklux.mapper.CalendarSubscribedMapper;
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
    @Autowired
    private CalendarItemMapper calendarItemMapper;
    @Autowired
    private CalendarSubscribedMapper calendarSubscribedMapper;

    private int iday;// seed of today

    public CalendarService() {
        java.util.Calendar now = java.util.Calendar.getInstance();
        iday = now.get(java.util.Calendar.YEAR) * 10000 + (now.get(java.util.Calendar.MONTH) + 1) * 100 + now.get(java.util.Calendar.DAY_OF_MONTH);
    }

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
        List<CalendarActivity> picked = pickRandomActivities(activities, calendar.getBadPick() + calendar.getGoodPick());

        TodayResponse response = new TodayResponse();

        response.setCalendarName(calendar.getTitle());
        response.setCalendarId(calendarId);
        response.setCalendarPicture(calendar.getPicture());
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

        Collection<CalendarItem> items = calendarItemMapper.getItems(calendarId);

        for (CalendarItem it:items) {
            response.addRecommend(it.getTitle(),pickRandomItems(it.getItem(),it.getPickCount()));
        }

        return response;
    }

    public List<TodayResponse> getSubscribed(long userId) throws BaseException {
        Collection<CalendarSubscribe> subscribes = calendarSubscribedMapper.getSubscribed(userId);
        List<TodayResponse> response = new ArrayList<>();
        for (CalendarSubscribe s:subscribes) {
            response.add(getToday(s.getCalendarId()));
        }
        return response;
    }

    private List<CalendarActivity> pickRandomActivities(Collection<CalendarActivity> activities, int num) {
        List<CalendarActivity> list = new ArrayList<>(activities);
        List<CalendarActivity> copy = new ArrayList<>(list);
        for (int i=0;i<list.size() - num;i++) {
            int index = Utils.getRandom(this.iday,i) % copy.size();
            copy.remove(index);
        }
        return copy;
    }

    private List<String> pickRandomItems(String items, int num) {
        String[] splits = items.split(" ");
        List<String> list = new ArrayList<>();
        for (int i=0;i<splits.length;i++) {
            list.add(splits[i]);
        }
        List<String> copy = new ArrayList<>(list);
        for (int i=0;i<list.size() - num;i++) {
            int index = Utils.getRandom(this.iday,i) % copy.size();
            copy.remove(index);
        }
        return copy;
    }
}