package com.marklux.services;

import com.marklux.domain.CalendarComment;
import com.marklux.mapper.CalendarCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mark on 17/11/6.
 */
@Service
public class CommentService {
    @Autowired
    private CalendarCommentMapper calendarCommentMapper;

    public List<CalendarComment> getComments(long calendarId,int page,int size) {
        return new ArrayList<>(calendarCommentMapper.getCommentsByCalendarId(calendarId,(page-1)*size,size));
    }

    public int createComment(CalendarComment comment) {
        return calendarCommentMapper.createComment(comment);
    }
}
