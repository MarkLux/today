package com.marklux.controller;

import com.marklux.common.Response;
import com.marklux.domain.CalendarSubscribe;
import com.marklux.domain.User;
import com.marklux.exception.BaseException;
import com.marklux.exception.UnkownException;
import com.marklux.services.SubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by mark on 17/11/5.
 */
@RestController
@RequestMapping("/calendar")
public class SubscribeController {
    @Autowired
    private SubscribeService subscribeService;

    @GetMapping("/subscribed")
    public Response getSubscribed(HttpServletRequest request) throws BaseException {
        User user = (User)request.getAttribute("user");
        return new Response(0,subscribeService.getSubscribed(user.getId()));
    }

    @GetMapping("/{calendarId}/subscribe")
    public Response subscribe(HttpServletRequest request, @PathVariable long calendarId) throws BaseException {
        User user = (User)request.getAttribute("user");
        if (!subscribeService.addSubscribe(user.getId(),calendarId)) {
            throw new UnkownException("无法添加订阅");
        }
        return new Response(0,null);
    }

    @GetMapping("/{calendarId}/unsubscribe")
    public Response unsubscribe(HttpServletRequest request,@PathVariable long calendarId) throws BaseException {
        User user = (User)request.getAttribute("user");
        if (!subscribeService.deleteSubscribe(user.getId(),calendarId)) {
            throw new UnkownException("无法取消订阅");
        }
        return new Response(0,null);
    }

    @PutMapping("/subscribe")
    public Response updateSubscribe(HttpServletRequest request, @Valid @RequestBody List<CalendarSubscribe> subscribes) throws BaseException {
        User user = (User)request.getAttribute("user");
        if (!subscribeService.updateSubscribes(user.getId(),subscribes)) {
            throw new UnkownException("更新订阅失败");
        }
        return new Response(0,null);
    }
}
