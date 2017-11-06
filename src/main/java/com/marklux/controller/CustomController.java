package com.marklux.controller;

import com.marklux.common.Response;
import com.marklux.common.Utils;
import com.marklux.domain.Calendar;
import com.marklux.domain.CalendarActivity;
import com.marklux.domain.User;
import com.marklux.dto.request.CreateCalendarRequest;
import com.marklux.exception.BaseException;
import com.marklux.exception.general.FormValidatorException;
import com.marklux.exception.general.PermissionDeniedException;
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

    @PostMapping("/new")
    public Response createNewCalendar(@RequestBody @Valid CreateCalendarRequest request, BindingResult bindingResult) throws BaseException {
        if (bindingResult.hasErrors()) {
            throw new FormValidatorException(bindingResult);
        }
        Calendar calendar = new Calendar();
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

        long newId = customService.createCalendar(calendar);

        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("calendarId", newId);

        return new Response(0, resultMap);
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

        int rows = customService.createCalendarActivities(list);

        Map<String,Integer> resultMap = new HashMap<>();
        resultMap.put("added",rows);
        return new Response(0,resultMap);
    }
}
