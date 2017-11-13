package com.marklux.services;

import com.marklux.common.Utils;
import com.marklux.domain.*;
import com.marklux.dto.response.TodayResponse;
import com.marklux.exception.BaseException;
import com.marklux.exception.general.ResourceNotExistException;
import com.marklux.mapper.*;
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
    @Autowired
    private CalendarCommentMapper calendarCommentMapper;

    public Calendar getCalendar(long Id) {
        return calendarMapper.getCalendar(Id);
    }

    public boolean updateCalendar(Calendar calendar) {
        return calendarMapper.updateCalendar(calendar) == 1;
    }

    public boolean deleteCalendar(long Id) {
        return calendarMapper.deleteCalendar(Id) == 1;
    }

    /**
     * 获取推荐黄历列表，返回订阅数量最多的几个
     */

    public List<Calendar> getMostSubscribed(int num) {
        return new ArrayList<>(calendarMapper.getMostSubscribed(num));
    }

    /**
     * 分页获取全部的黄历，创建时间倒序
     */

    public List<Calendar> getCalendars(int page,int size) {
        return new ArrayList<>(calendarMapper.getCalendars(size*(page-1),size));
    }

    /**
     * 分页获取全部的黄历，创建时间倒序
     */

    public List<Calendar> getCalendarsLike(String key,int page,int size) {
        key = '%' + key + '%';
        return new ArrayList<>(calendarMapper.getCalendarLike(key,size*(page-1),size));
    }

    /**
     * 获取指定黄历的今天的数据
     */

    public TodayResponse getToday(long calendarId) throws BaseException {
        // 构建随机池
        Calendar calendar = calendarMapper.getCalendar(calendarId);
        if (calendar == null) {
            return null;
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

    /**
     * 获取黄历详情页面
     */

    public CalendarDetail getCalendarDetail(long calendarId) {
        return calendarMapper.getCalendarDetail(calendarId);
    }

    /**
     * 内部方法，从日历项中根据种子随机获取指定数量的
     */

    private List<CalendarActivity> pickRandomActivities(Collection<CalendarActivity> activities, int num) {
        java.util.Calendar now = java.util.Calendar.getInstance();
        int iday = now.get(java.util.Calendar.YEAR) * 10000 + (now.get(java.util.Calendar.MONTH) + 1) * 100 + now.get(java.util.Calendar.DAY_OF_MONTH);
        List<CalendarActivity> list = new ArrayList<>(activities);
        List<CalendarActivity> copy = new ArrayList<>(list);
        for (int i=0;i<list.size() - num;i++) {
            int index = Utils.getRandom(iday,i) % copy.size();
            copy.remove(index);
        }
        return copy;
    }

    /**
     * 内部方法，从推荐项中根据种子随机获取指定数量的
     */

    private List<String> pickRandomItems(String items, int num) {
        String[] splits = items.split(" ");
        List<String> list = new ArrayList<>();
        for (int i=0;i<splits.length;i++) {
            list.add(splits[i]);
        }
        java.util.Calendar now = java.util.Calendar.getInstance();
        int iday = now.get(java.util.Calendar.YEAR) * 10000 + (now.get(java.util.Calendar.MONTH) + 1) * 100 + now.get(java.util.Calendar.DAY_OF_MONTH);
        List<String> copy = new ArrayList<>(list);
        for (int i=0;i<list.size() - num;i++) {
            int index = Utils.getRandom(iday,i) % copy.size();
            copy.remove(index);
        }
        return copy;
    }
}