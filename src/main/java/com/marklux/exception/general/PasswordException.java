package com.marklux.exception.general;

import com.marklux.exception.BaseException;

/**
 * Created by mark on 17/11/1.
 */
public class PasswordException extends BaseException {
    public PasswordException() {
        this.setCode(10003);
        this.setMessage("密码错误");
    }
}
