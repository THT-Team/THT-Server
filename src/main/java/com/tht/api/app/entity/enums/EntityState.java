package com.tht.api.app.entity.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum EntityState {

    PENDING,
    INACTIVE,
    ACTIVE,
    DELETED
}
