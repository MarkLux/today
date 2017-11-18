package com.marklux.mapper;

import com.marklux.domain.CalendarSubscribe;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

/**
 * Created by mark on 17/11/3.
 */
public interface CalendarSubscribedMapper {
    Collection<CalendarSubscribe> getSubscribed(@Param("userId") long userId);
    int createSubscribed(@Param("userId") long userId,@Param("calendarId") long calendarId,@Param("order") int order);
    int updateSubscribes(List<CalendarSubscribe> subscribes);
    int deleteSubscribed(@Param("userId") long userId,@Param("calendarId") long calendarId);
    CalendarSubscribe getSubscribe(@Param("userId") long userId,@Param("calendarId") long calendarId);
    int getMaxOrder(@Param("userId") long userId);
    int deleteSubscribes(@Param("userId") long userId);
}
