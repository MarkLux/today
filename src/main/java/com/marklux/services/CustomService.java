package com.marklux.services;

import com.marklux.domain.Calendar;
import com.marklux.domain.CalendarActivity;
import com.marklux.domain.CalendarItem;
import com.marklux.mapper.CalendarActivityMapper;
import com.marklux.mapper.CalendarItemMapper;
import com.marklux.mapper.CalendarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
     * 批量创建黄历项目
     */

    public int createCalendarActivities(List<CalendarActivity> list) {
        return calendarActivityMapper.createActivities(list);
    }

    /**
     * 批量创建黄历推荐
     */

    public int createCalendarItems(List<CalendarItem> list) {
        return calendarItemMapper.createItems(list);
    }

    /**
     * 获取自己创建的黄历
     */

    public List<Calendar> getCalendarsByCreatorId(Long userId) {
        return new ArrayList<>(calendarMapper.getCalendarByCreatorId(userId));
    }
}