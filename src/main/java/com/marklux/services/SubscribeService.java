package com.marklux.services;

import com.marklux.domain.Calendar;
import com.marklux.domain.CalendarSubscribe;
import com.marklux.dto.response.TodayResponse;
import com.marklux.exception.BaseException;
import com.marklux.exception.general.ResourceExistedException;
import com.marklux.exception.general.ResourceNotExistException;
import com.marklux.mapper.CalendarSubscribedMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by mark on 17/11/5.
 */
@Service
public class SubscribeService {
    @Autowired
    private CalendarSubscribedMapper calendarSubscribedMapper;
    @Autowired
    private CalendarService calendarService;

    /**
     * 获取用户订阅的黄历列表
     */

    public List<TodayResponse> getSubscribed(long userId) throws BaseException {
        Collection<CalendarSubscribe> subscribes = calendarSubscribedMapper.getSubscribed(userId);
        List<TodayResponse> response = new ArrayList<>();
        for (CalendarSubscribe s:subscribes) {
            response.add(calendarService.getToday(s.getCalendarId()));
        }
        return response;
    }

    /**
     * 添加订阅
     */
    @Transactional
    public boolean addSubscribe(long userId, long calendarId) throws BaseException {
        Calendar calendar = calendarService.getCalendar(calendarId);
        if (calendar == null) {
            throw new ResourceNotExistException("日历");
        }
        CalendarSubscribe previous = calendarSubscribedMapper.getSubscribe(userId, calendarId);
        if (previous == null) {
            throw new ResourceExistedException("订阅");
        }
        calendar.setSubscribed(calendar.getSubscribed()+1);
        calendarService.updateCalendar(calendar);
        int order = calendarSubscribedMapper.getMaxOrder(userId) + 1;
        return calendarSubscribedMapper.createSubscribed(userId,calendarId,order) == 1;
    }

    /**
     * 取消订阅
     */
    @Transactional
    public boolean deleteSubscribe(long userId,long calendarId) throws BaseException {
        Calendar calendar = calendarService.getCalendar(calendarId);
        if (calendar == null) {
            throw new ResourceNotExistException("日历");
        }
        CalendarSubscribe previous = calendarSubscribedMapper.getSubscribe(userId, calendarId);
        if (previous == null) {
            throw new ResourceExistedException("订阅");
        }
        calendar.setSubscribed(calendar.getSubscribed()-1);
        calendarService.updateCalendar(calendar);
        return calendarSubscribedMapper.deleteSubscribed(userId,calendarId) == 1;
    }

    @Transactional
    public boolean updateSubscribes(long userId,List<CalendarSubscribe> subscribes) {
        // 先删除原来的所有订阅再重新插入
        for (CalendarSubscribe cs:subscribes) {
            cs.setUserId(userId);
        }
        calendarSubscribedMapper.deleteSubscribes(userId);
        return calendarSubscribedMapper.updateSubscribes(subscribes) == subscribes.size();
    }

}
