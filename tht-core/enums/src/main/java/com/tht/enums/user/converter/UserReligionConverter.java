package com.tht.enums.user.converter;


import com.tht.enums.user.UserReligion;
import jakarta.persistence.AttributeConverter;

import java.util.Objects;

public class UserReligionConverter implements AttributeConverter<UserReligion, String> {


    @Override
    public String convertToDatabaseColumn(UserReligion userReligion) {

        if (Objects.isNull(userReligion)) {
            throw new NullPointerException("Enum Converting String - UserRole is null");
        }

        return userReligion.name();
    }

    @Override
    public UserReligion convertToEntityAttribute(String dbData) {

        return UserReligion.toConverter(dbData);
    }
}
