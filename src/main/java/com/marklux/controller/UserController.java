package com.marklux.controller;

import com.marklux.common.Encrypt;
import com.marklux.common.Response;
import com.marklux.common.Utils;
import com.marklux.domain.User;
import com.marklux.dto.request.*;
import com.marklux.dto.response.UserLoginResponse;
import com.marklux.exception.BaseException;
import com.marklux.exception.UnkownException;
import com.marklux.exception.general.FormValidatorException;
import com.marklux.exception.general.PasswordException;
import com.marklux.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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
    @Value("${qiniu.domain}")
    private String qiniuDomain;

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

        return new Response(0,user.getId());
    }

    @PostMapping("/login")
    public Response login(HttpServletRequest request,@RequestBody @Valid UserLoginRequest loginRequest, BindingResult bindingResult) throws BaseException {
        if (bindingResult.hasErrors()) {
            throw new FormValidatorException(bindingResult);
        }

        UserLoginResponse response = userService.login(loginRequest.getName(),loginRequest.getPassword(),loginRequest.getClient(),request.getRemoteHost());

        return new Response(0,response);
    }

    @GetMapping("/{userId}/info")
    public Response getUserInfo(@PathVariable long userId) {
        User user = userService.getUserById(userId);
        return new Response(0,user);
    }

    @GetMapping("/me")
    public Response getMine(HttpServletRequest request) {
        User user = (User)request.getAttribute("user");
        return new Response(0,user);
    }

    @PutMapping("/avatar")
    public Response updateUserAvatar(@RequestBody @Valid UpdateUserAvatarRequest updateUserAvatarRequest,HttpServletRequest request) throws BaseException {
        User user = (User)request.getAttribute("user");

        user.setAvatar(qiniuDomain + updateUserAvatarRequest.getAvatar());
        if (!userService.updateUser(user)) {
            throw new UnkownException("更新用户信息失败");
        }
        return new Response(0,null);
    }

    @PutMapping("/signature")
    public Response updateUserSignature(@RequestBody @Valid UpdateUserSignatureRequest updateUserSignatureRequest,HttpServletRequest request) throws BaseException {
        User user = (User)request.getAttribute("user");
        user.setSignature(updateUserSignatureRequest.getSignature());
        if (!userService.updateUser(user)) {
            throw new UnkownException("更新用户信息失败");
        }
        return new Response(0,null);
    }

    @PutMapping("/password")
    public Response updateUserSignature(@RequestBody @Valid UpdateUserPasswordRequest updateUserPasswordRequest, HttpServletRequest request) throws BaseException {
        User user = (User)request.getAttribute("user");
        String old = Encrypt.encrypt(updateUserPasswordRequest.getOldPassword());
        if (!old.equals(user.getPassword())) {
            throw new PasswordException();
        }
        user.setPassword(Encrypt.encrypt(updateUserPasswordRequest.getNewPassword()));
        if (!userService.updateUser(user)) {
            throw new UnkownException("更新用户信息失败");
        }
        return new Response(0,null);
    }
}
