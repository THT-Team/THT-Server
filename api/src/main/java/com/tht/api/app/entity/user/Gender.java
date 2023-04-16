package com.tht.api.app.entity.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum Gender {
    MALE,
    FEMALE,
    BISEXUAL;

    public Gender toConverter(final String dbData) {
        if (ArrayUtils.contains(Gender.values(), dbData)) {
            return Gender.valueOf(dbData);
        }

        throw new NullPointerException("NPE");
    }
}
