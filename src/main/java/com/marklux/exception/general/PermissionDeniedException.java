package com.marklux.exception.general;

import com.marklux.exception.BaseException;

/**
 * Created by mark on 17/11/6.
 */
public class PermissionDeniedException extends BaseException {
    public PermissionDeniedException() {
        this.setCode(10009);
        this.setMessage("没有权限");
    }
}
