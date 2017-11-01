package com.marklux.exception;

/**
 * Created by mark on 17/11/1.
 */
public class UnkownException extends BaseException {
    public UnkownException(String message) {
        this.setCode(5000);
        this.setMessage(message);
    }
}
