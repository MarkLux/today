package com.marklux.dto.request;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by mark on 17/11/1.
 */
public class UserLoginRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String password;
    @NotNull
    @Max(2)
    @Min(0)
    private Integer client;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getClient() {
        return client;
    }

    public void setClient(Integer client) {
        this.client = client;
    }
}
