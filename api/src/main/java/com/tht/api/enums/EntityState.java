package com.tht.api.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum EntityState {

    PENDING,
    INACTIVE,
    ACTIVE,
    DELETED;
}
