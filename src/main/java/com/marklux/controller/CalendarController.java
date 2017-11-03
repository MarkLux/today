package com.marklux.controller;

import com.marklux.common.Response;
import com.marklux.common.Utils;
import com.marklux.domain.Calendar;
import com.marklux.dto.request.CreateCalendarRequest;
import com.marklux.exception.BaseException;
import com.marklux.exception.general.FormValidatorException;
import com.marklux.services.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public Response createNewCalendar(@RequestBody @Valid CreateCalendarRequest request, BindingResult bindingResult) throws BaseException {
        if (bindingResult.hasErrors()) {
            throw new FormValidatorException(bindingResult);
        }
        Calendar calendar = new Calendar();
        Long currentTime = Utils.createTimestamp();
        calendar.setTitle(request.getTitle());
        calendar.setDescription(request.getDescription());
        calendar.setSubscribed(0);
        calendar.setIsPublic(request.getIsPublic());
        calendar.setGoodPick(request.getGoodPick());
        calendar.setBadPick(request.getBadPick());
        calendar.setCreatedAt(currentTime);
        calendar.setUpdatedAt(currentTime);

        long newId = calendarService.createCalendar(calendar);

        Map<String, Long> resultMap = new HashMap<>();
        resultMap.put("calendarId", newId);

        return new Response(0, resultMap);
    }

    @GetMapping("/{id}")
    public Response getToday(@PathVariable long id) throws BaseException {
        return new Response(0, calendarService.getToday(id));
    }

    @GetMapping("/subscribed/{id}")
    public Response getSubscribed(@PathVariable long id) throws BaseException {
        return new Response(0,calendarService.getSubscribed(id));
    }
}
