package com.tht.infra.user.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum LikeState {

    LIKE,
    MATCH,
    REJECT,
    DISLIKE
}
