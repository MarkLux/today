package com.marklux.exception;

/**
 * Created by mark on 17/10/31.
 */
public class BaseException extends Exception {
    private int code;
    private String Message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
