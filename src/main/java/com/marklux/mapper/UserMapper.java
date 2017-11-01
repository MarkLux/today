package com.marklux.mapper;

import com.marklux.domain.User;

import java.util.Collection;

/**
 * Created by mark on 17/10/24.
 */
public interface UserMapper   {
    void createUser(User user);
    User getUserById(long userId);
    Collection<User> getUserBy(String key,String value);
    User getUserByName(String name);
    int updateUser(User user);
    int deleteUser(User user);
}
