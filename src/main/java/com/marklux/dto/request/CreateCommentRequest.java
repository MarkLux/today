package com.marklux.dto.request;

import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by mark on 17/11/6.
 */
public class CreateCommentRequest {
    @NotBlank
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
