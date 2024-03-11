package com.tht.api.app.entity.enums.converter;

import com.tht.api.app.entity.enums.UserFrequency;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;

@Converter
public class UserFrequencyConverter implements AttributeConverter<UserFrequency, String> {
    @Override
    public String convertToDatabaseColumn(UserFrequency userFrequency) {
        if (Objects.isNull(userFrequency)) {
            throw new NullPointerException("Enum Converting String - UserRole is null");
        }

        return userFrequency.name();    }

    @Override
    public UserFrequency convertToEntityAttribute(String dbData) {
        return UserFrequency.toConverter(dbData);
    }
}
