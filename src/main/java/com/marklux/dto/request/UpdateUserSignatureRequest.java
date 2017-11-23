package com.marklux.dto.request;

import javax.validation.constraints.NotNull;

/**
 * Created by mark on 17/11/23.
 */
public class UpdateUserSignatureRequest {
    @NotNull
    private String signature;

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
