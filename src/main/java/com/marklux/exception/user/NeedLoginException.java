package com.marklux.exception.user;

import com.marklux.exception.BaseException;

/**
 * Created by mark on 17/11/1.
 */
public class NeedLoginException extends BaseException {
    public NeedLoginException() {
        this.setCode(20001);
        this.setMessage("Need Login");
    }
}
