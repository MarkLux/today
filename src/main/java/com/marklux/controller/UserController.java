package com.marklux.controller;

import com.marklux.common.Response;
import com.marklux.common.Utils;
import com.marklux.domain.User;
import com.marklux.dto.request.UserLoginRequest;
import com.marklux.dto.request.UserRegisterRequest;
import com.marklux.dto.response.UserLoginResponse;
import com.marklux.exception.BaseException;
import com.marklux.exception.general.FormValidatorException;
import com.marklux.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mark on 17/11/1.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Response register(@RequestBody @Valid UserRegisterRequest registerRequest, BindingResult bindingResult) throws BaseException {
        if (bindingResult.hasErrors()) {
            throw new FormValidatorException(bindingResult);
        }
        User user = new User();
        user.setName(registerRequest.getName());
        user.setPassword(registerRequest.getPassword());
        user.setSex(registerRequest.getSex());
        user.setSignature(registerRequest.getSignature());
        long timestamp = Utils.createTimestamp();
        user.setCreatedAt(timestamp);
        user.setUpdatedAt(timestamp);

        userService.register(user);

        Map<String,Long> resultMap = new HashMap<>();
        resultMap.put("userId",user.getId());

        return new Response(0,resultMap);
    }

    @PostMapping("/login")
    public Response login(HttpServletRequest request,@RequestBody @Valid UserLoginRequest loginRequest, BindingResult bindingResult) throws BaseException {
        if (bindingResult.hasErrors()) {
            throw new FormValidatorException(bindingResult);
        }

        UserLoginResponse response = userService.login(loginRequest.getName(),loginRequest.getPassword(),loginRequest.getClient(),request.getRemoteHost());

        return new Response(0,response);
    }
}
