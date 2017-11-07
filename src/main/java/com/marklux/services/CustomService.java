package com.marklux.services;

import com.marklux.domain.Calendar;
import com.marklux.domain.CalendarActivity;
import com.marklux.domain.CalendarItem;
import com.marklux.mapper.CalendarActivityMapper;
import com.marklux.mapper.CalendarItemMapper;
import com.marklux.mapper.CalendarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mark on 17/11/6.
 * 黄历的自定义服务
 */
@Service
public class CustomService {
    @Autowired
    private CalendarMapper calendarMapper;
    @Autowired
    private CalendarActivityMapper calendarActivityMapper;
    @Autowired
    private CalendarItemMapper calendarItemMapper;

    /**
     * 创建黄历（基础信息）
     */

    public long createCalendar(Calendar calendar) {
        calendarMapper.createCalendar(calendar);
        return calendar.getId();
    }

    /**
     * 更新/创建/修改黄历项目
     */
    @Transactional
    public int updateCalendarActivities(long calendarId,List<CalendarActivity> list) {
        calendarActivityMapper.deleteActivitiesByCalendarId(calendarId);
        return calendarActivityMapper.createActivities(list);
    }

    /**
     * 更新/创建/修改黄历推荐
     */
    @Transactional
    public int updateCalendarItems(long calendarId,List<CalendarItem> list) {
        calendarItemMapper.deleteItemsByCalendarId(calendarId);
        return calendarItemMapper.createItems(list);
    }

    /**
     * 获取自己创建的黄历
     */

    public List<Calendar> getCalendarsByCreatorId(Long userId) {
        return new ArrayList<>(calendarMapper.getCalendarByCreatorId(userId));
    }
}