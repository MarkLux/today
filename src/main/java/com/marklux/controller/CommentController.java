package com.marklux.controller;

import com.marklux.common.Response;
import com.marklux.common.Utils;
import com.marklux.domain.CalendarComment;
import com.marklux.domain.User;
import com.marklux.exception.BaseException;
import com.marklux.exception.general.FormValidatorException;
import com.marklux.services.CommentService;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Created by mark on 17/11/6.
 */
@RestController
@RequestMapping("/calendar")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/{calendarId}/comment")
    public Response getCalendarComments(@RequestParam(defaultValue = "1",required = false) int page,
                                        @RequestParam(defaultValue = "20",required = false) int size,
                                        @PathVariable long calendarId) {
        return new Response(0,commentService.getComments(calendarId,page,size));
    }

    @PostMapping("/{calendarId}/comment")
    public Response addComment(@PathVariable long calendarId,
                               @RequestBody @Valid @NotBlank String comment,
                               BindingResult bindingResult,
                               HttpServletRequest request) throws BaseException {
        if (bindingResult.hasErrors()) {
            throw new FormValidatorException(bindingResult);
        }
        User user = (User)request.getAttribute("user");
        CalendarComment newComment = new CalendarComment();
        newComment.setCalendarId(calendarId);
        newComment.setCreatedAt(Utils.createTimestamp());
        newComment.setUserId(user.getId());
        newComment.setComment(comment);

        this.commentService.createComment(newComment);

        return new Response(0,null);
    }
}
