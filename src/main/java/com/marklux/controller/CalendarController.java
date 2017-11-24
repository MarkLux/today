package com.marklux.controller;

import com.marklux.common.Response;
import com.marklux.common.Utils;
import com.marklux.domain.Calendar;
import com.marklux.domain.CalendarComment;
import com.marklux.domain.CalendarDetail;
import com.marklux.domain.User;
import com.marklux.dto.request.CreateCalendarRequest;
import com.marklux.exception.BaseException;
import com.marklux.exception.general.FormValidatorException;
import com.marklux.exception.general.ResourceNotExistException;
import com.marklux.services.CalendarService;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mark on 17/10/31.
 */
@RestController
@RequestMapping("/calendar")
public class CalendarController {
    @Autowired
    CalendarService calendarService;

    @GetMapping("/{id}")
    public Response getToday(@PathVariable long id) throws BaseException {
        return new Response(0, calendarService.getToday(id));
    }

    @GetMapping("/{calendarId}/detail")
    public Response getCalendarDetail(@PathVariable long calendarId,HttpServletRequest request) throws BaseException {
        long userId = -1;
        User user = (User)request.getAttribute("user");
        if (user != null) {
            userId = user.getId();
        }
        CalendarDetail calendarDetail = calendarService.getCalendarDetail(calendarId,userId);
        if (calendarDetail == null) {
            throw new ResourceNotExistException("黄历");
        }
        calendarDetail.setPreview(calendarService.getToday(calendarId));
        return new Response(0,calendarDetail);
    }
}
