package com.marklux.mapper;

import com.marklux.domain.CalendarComment;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;

/**
 * Created by mark on 17/11/6.
 */
public interface CalendarCommentMapper {
    Collection<CalendarComment> getCommentsByCalendarId(@Param("calendarId") long calendarId,@Param("page") int page,@Param("size") int size);
    int createComment(CalendarComment calendarComment);
    int deleteComment(long id);
}
