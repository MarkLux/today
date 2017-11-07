package com.marklux.controller;

import com.marklux.common.Response;
import com.marklux.common.Utils;
import com.marklux.domain.Calendar;
import com.marklux.domain.CalendarActivity;
import com.marklux.domain.CalendarItem;
import com.marklux.domain.User;
import com.marklux.dto.request.CreateCalendarRequest;
import com.marklux.exception.BaseException;
import com.marklux.exception.UnkownException;
import com.marklux.exception.general.FormValidatorException;
import com.marklux.exception.general.PermissionDeniedException;
import com.marklux.exception.general.ResourceNotExistException;
import com.marklux.services.CalendarService;
import com.marklux.services.CustomService;
import com.marklux.services.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mark on 17/11/6.
 */
@RestController
@RequestMapping("/custom")
public class CustomController {
    @Autowired
    CustomService customService;
    @Autowired
    PermissionService permissionService;
    @Autowired
    CalendarService calendarService;

    @PostMapping("/new")
    public Response createNewCalendar(@RequestBody @Valid CreateCalendarRequest request,
                                      BindingResult bindingResult,
                                      HttpServletRequest httpServletRequest) throws BaseException {
        if (bindingResult.hasErrors()) {
            throw new FormValidatorException(bindingResult);
        }
        Calendar calendar = new Calendar();

        User user = (User)httpServletRequest.getAttribute("user");

        Long currentTime = Utils.createTimestamp();
        calendar.setTitle(request.getTitle());
        calendar.setPicture(request.getPicture());
        calendar.setDescription(request.getDescription());
        calendar.setSubscribed(0);
        calendar.setIsPublic(request.getIsPublic());
        calendar.setGoodPick(request.getGoodPick());
        calendar.setBadPick(request.getBadPick());
        calendar.setCreatedAt(currentTime);
        calendar.setUpdatedAt(currentTime);
        calendar.setCreatorId(user.getId());

        long newId = customService.createCalendar(calendar);

        return new Response(0, newId);
    }

    @PutMapping("/{calendarId}")
    public Response updateCalendar(@RequestBody @Valid CreateCalendarRequest request,@PathVariable long calendarId,
                                   BindingResult bindingResult,
                                   HttpServletRequest httpServletRequest) throws BaseException {
        if (bindingResult.hasErrors()) {
            throw new FormValidatorException(bindingResult);
        }

        User user = (User)httpServletRequest.getAttribute("user");

        Long currentTime = Utils.createTimestamp();
        Calendar calendar = calendarService.getCalendar(calendarId);

        if (calendar == null) {
            throw new ResourceNotExistException("日历");
        }

        if (!permissionService.checkOwnership(user.getId(),calendarId)) {
            throw new PermissionDeniedException();
        }

        calendar.setTitle(request.getTitle());
        calendar.setPicture(request.getPicture());
        calendar.setDescription(request.getDescription());
        calendar.setIsPublic(request.getIsPublic());
        calendar.setGoodPick(request.getGoodPick());
        calendar.setBadPick(request.getBadPick());
        calendar.setUpdatedAt(currentTime);

        if (!calendarService.updateCalendar(calendar)) {
            throw new UnkownException("无法更新日历基本信息");
        }

        return new Response(0,null);
    }

    @DeleteMapping("/{calendarId}")
    public Response deleteCalendar(@PathVariable long calendarId,
                                   BindingResult bindingResult,
                                   HttpServletRequest httpServletRequest) throws BaseException {
        if (bindingResult.hasErrors()) {
            throw new FormValidatorException(bindingResult);
        }

        User user = (User)httpServletRequest.getAttribute("user");

        Calendar calendar = calendarService.getCalendar(calendarId);

        if (calendar == null) {
            throw new ResourceNotExistException("日历");
        }

        if (!permissionService.checkOwnership(user.getId(),calendarId)) {
            throw new PermissionDeniedException();
        }

        if (!this.calendarService.deleteCalendar(calendarId)) {
            throw new UnkownException("无法删除黄历");
        }

        return new Response(0,null);
    }

    @PostMapping("/{calendarId}/activities")
    public Response createActivities(@RequestBody @Valid List<CalendarActivity> list,
                                     @PathVariable long calendarId, BindingResult bindingResult,
                                     HttpServletRequest request) throws BaseException {
        if (bindingResult.hasErrors()) {
            throw new FormValidatorException(bindingResult);
        }

        // 检查权限
        User user = (User)request.getAttribute("user");
        if (!permissionService.checkOwnership(user.getId(),calendarId)) {
            throw new PermissionDeniedException();
        }

        // 处理数据

        for (CalendarActivity act:list) {
            act.setCalendarId(calendarId);
        }

        int rows = customService.updateCalendarActivities(calendarId,list);

        return new Response(0,rows);
    }

    @PostMapping("/{calendarId}/items")
    public Response createItems(@RequestBody @Valid List<CalendarItem> list,
                                @PathVariable long calendarId,BindingResult bindingResult,
                                HttpServletRequest request) throws BaseException {
        if (bindingResult.hasErrors()) {
            throw new FormValidatorException(bindingResult);
        }

        User user = (User)request.getAttribute("user");
        if (!permissionService.checkOwnership(user.getId(),calendarId)) {
            throw new PermissionDeniedException();
        }

        for (CalendarItem item:list) {
            item.setCalendarId(calendarId);
        }

        int rows = customService.updateCalendarItems(calendarId,list);

        return new Response(0,rows);
    }

    @GetMapping("/created")
    public Response getCreatedCalendars(HttpServletRequest request) {
        User user = (User)request.getAttribute("user");
        List<Calendar> calendarList = customService.getCalendarsByCreatorId(user.getId());
        return new Response(0,calendarList);
    }
}
