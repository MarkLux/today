package com.marklux.exception.general;

import com.marklux.exception.BaseException;

/**
 * Created by mark on 17/11/1.
 */
public class ResourceExistedException extends BaseException {
    public ResourceExistedException(String resource) {
        this.setCode(10002);
        this.setMessage(resource + "已经存在");
    }
}
