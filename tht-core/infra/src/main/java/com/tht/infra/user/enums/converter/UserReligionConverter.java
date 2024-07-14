package com.tht.infra.user.enums.converter;


import com.tht.infra.user.enums.UserReligion;
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
