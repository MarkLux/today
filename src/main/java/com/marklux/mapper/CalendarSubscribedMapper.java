package com.marklux.mapper;

import com.marklux.domain.CalendarSubscribe;

import java.util.Collection;
import java.util.List;

/**
 * Created by mark on 17/11/3.
 */
public interface CalendarSubscribedMapper {
    Collection<CalendarSubscribe> getSubscribed(long userId);
    int createSubscribed(long userId,long calendarId,int order);
    int updateSubscribes(List<CalendarSubscribe> subscribes);
    int deleteSubscribed(long userId,long calendarId);
    CalendarSubscribe getSubscribe(long userId,long calendarId);
    int getMaxOrder(long userId);
    int deleteSubscribes(long userId);
}
