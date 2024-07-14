package com.tht.infra;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum EntityState {

    PENDING,
    INACTIVE,
    ACTIVE,
    DELETED
}
