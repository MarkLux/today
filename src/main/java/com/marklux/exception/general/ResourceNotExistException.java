package com.marklux.exception.general;

import com.marklux.exception.BaseException;

/**
 * Created by mark on 17/10/31.
 */
public class ResourceNotExistException extends BaseException {
    private String resourceName;

    public ResourceNotExistException(String resourceName) {
        this.setCode(10001);
        this.resourceName = resourceName;
        this.setMessage(resourceName + "不存在");
    }
}
