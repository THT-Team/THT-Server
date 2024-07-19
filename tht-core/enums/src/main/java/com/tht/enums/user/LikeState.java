package com.tht.enums.user;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum LikeState {

    LIKE,
    MATCH,
    REJECT,
    DISLIKE
}
