package com.marklux.services;

import com.marklux.mapper.CalendarMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mark on 17/11/6.
 */
@Service
public class PermissionService {
    @Autowired
    CalendarMapper calendarMapper;

    public boolean checkOwnership(long userId,long calendarId) {
       return calendarMapper.checkOwnerShip(userId,calendarId) == 1;
    }
}
