package com.marklux.mapper;

import com.marklux.domain.CalendarSubscribe;

import java.util.Collection;

/**
 * Created by mark on 17/11/3.
 */
public interface CalendarSubscribedMapper {
    Collection<CalendarSubscribe> getSubscribed(long userId);
    int createSubscribed(long userId,long calendarId);
    int deleteSubscribed(long userId,long calendarId);
}
