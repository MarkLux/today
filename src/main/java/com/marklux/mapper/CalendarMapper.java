package com.marklux.mapper;

import com.marklux.domain.Calendar;
import com.marklux.domain.CalendarDetail;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;

/**
 * Created by mark on 17/10/31.
 */

public interface CalendarMapper {
    Calendar getCalendar(long Id);
    Collection<Calendar> getCalendarByCreatorId(@Param("creatorId")long creatorId);

    void createCalendar(Calendar calendar);

    int updateCalendar(Calendar calendar);

    int deleteCalendar(long Id);

    Collection<Calendar> getMostSubscribed(@Param("num") int num);

    Collection<Calendar> getCalendars(@Param("page") int page,@Param("size") int size,@Param("userId") long userId);

    Collection<Calendar> getCalendarLike(@Param("key") String key,@Param("page") int page,@Param("size") int size);

    CalendarDetail getCalendarDetail(@Param("id") long id,@Param("userId") long userId);

    int checkOwnerShip(@Param("userId") long userId,@Param("calendarId") long calendarId);
}
