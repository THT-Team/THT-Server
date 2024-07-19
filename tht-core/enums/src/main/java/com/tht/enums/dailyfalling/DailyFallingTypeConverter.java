package com.tht.enums.dailyfalling;


import jakarta.persistence.AttributeConverter;

import java.util.Objects;

public class DailyFallingTypeConverter implements AttributeConverter<DailyFallingType, String> {

    @Override
    public String convertToDatabaseColumn(final DailyFallingType attribute) {
        if (Objects.isNull(attribute)) {
            throw new NullPointerException("Enum Converting String - DailyFallingType is null");
        }

        return attribute.name();
    }

    @Override
    public DailyFallingType convertToEntityAttribute(final String dbData) {

        return DailyFallingType.toConverter(dbData);
    }
}
