package com.tht.api.app.entity.user;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;

@Converter
public class GenderConverter implements AttributeConverter<Gender, String> {

    @Override
    public String convertToDatabaseColumn(final Gender attribute) {
        if (Objects.isNull(attribute)) {
            throw new NullPointerException("Enum Converting String - Gender is null");
        }

        return attribute.name();
    }

    @Override
    public Gender convertToEntityAttribute(final String dbData) {

        return Gender.toConverter(dbData);
    }

}
