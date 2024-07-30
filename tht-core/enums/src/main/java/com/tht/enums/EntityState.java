package com.tht.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum EntityState {

    ACTIVE,
    INACTIVE,
    WITHDRAW_REQUEST,
    DELETED,
}
