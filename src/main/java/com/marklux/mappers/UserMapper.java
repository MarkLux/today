package com.marklux.mappers;

import com.marklux.domain.User;

/**
 * Created by mark on 17/10/24.
 */
public interface UserMapper   {
    User findUserById(Long id);

    User findUserBy(String column,String value);

    User updateUser(Long id,User user);

    void createUser(User user);
}
