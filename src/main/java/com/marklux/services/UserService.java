package com.marklux.services;

import com.marklux.common.Encrypt;
import com.marklux.domain.User;
import com.marklux.dto.response.UserLoginResponse;
import com.marklux.exception.BaseException;
import com.marklux.exception.general.PasswordException;
import com.marklux.exception.general.ResourceExistedException;
import com.marklux.exception.general.ResourceNotExistException;
import com.marklux.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mark on 17/10/31.
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TokenService tokenService;

    public UserLoginResponse login(String name,String password,int client,String ip) throws BaseException {
        User user = userMapper.getUserByName(name);
        if (user == null) {
            throw new ResourceNotExistException("用户");
        } else {
            if (!Encrypt.check(password,user.getPassword())) {
                throw new PasswordException();
            }
        }
        String token = tokenService.createToken(user.getId(),client,ip);
        return new UserLoginResponse(token,user);
    }

    public void register(User user) throws BaseException {
        // 检查不可重复的字段
        User u = userMapper.getUserByName(user.getName());
        if (u != null) {
            throw new ResourceExistedException("用户名");
        }
        user.setPassword(Encrypt.encrypt(user.getPassword()));
        userMapper.createUser(user);
    }

    public User getUserById(long userId) {
        return userMapper.getUserById(userId);
    }
}
