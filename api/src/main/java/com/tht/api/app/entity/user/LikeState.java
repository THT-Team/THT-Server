package com.tht.api.app.entity.user;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum LikeState {

    LIKE,
    MATCH,
    REJECT,
    DISLIKE
}
