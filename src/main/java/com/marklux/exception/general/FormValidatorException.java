package com.marklux.exception.general;

import com.marklux.exception.BaseException;
import org.springframework.validation.BindingResult;

/**
 * Created by mark on 17/10/31.
 */
public class FormValidatorException extends BaseException {
    public FormValidatorException(BindingResult bindingResult) {
        this.setMessage(bindingResult.getFieldError().getField()+bindingResult.getFieldError().getDefaultMessage());
        this.setCode(10005);
    }

    public FormValidatorException(String message) {
        this.setMessage(message);
        this.setCode(10005);
    }
}
