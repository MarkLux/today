package com.marklux.controller;

import com.marklux.common.Response;
import com.marklux.services.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by mark on 17/11/5.
 */
@RestController
@RequestMapping("/piazza")
public class PiazzaController {
    @Autowired
    private CalendarService calendarService;

    @GetMapping("/most-subscribed")
    public Response getMostSubscribed() {
        return new Response(0,calendarService.getMostSubscribed(7));
    }

    @GetMapping("/all")
    public Response getAll(@RequestParam(required = false,defaultValue = "1") int page,
                           @RequestParam(required = false,defaultValue = "15") int size) {
        return new Response(0,calendarService.getCalendars(page,size));
    }

    @GetMapping("/search")
    public Response search(@RequestParam(required = true) String keyword,
                           @RequestParam(required = false,defaultValue = "1") int page,
                           @RequestParam(required = false,defaultValue = "15") int size) {
        return new Response(0,calendarService.getCalendarsLike(keyword,page,size));
    }
}
