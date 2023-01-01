package com.tht.api.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum Gender {

    MALE,
    FEMALE,
    BISEXUAL
}
