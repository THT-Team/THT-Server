package com.tht.enums.user.converter;



import com.tht.enums.user.SNSType;
import jakarta.persistence.AttributeConverter;

import java.util.Objects;

public class SNSTypeConverter implements AttributeConverter<SNSType, String> {


    @Override
    public String convertToDatabaseColumn(SNSType attribute) {
        if (Objects.isNull(attribute)) {
            throw new NullPointerException("Enum Converting String - UserRole is null");
        }

        return attribute.name();
    }

    @Override
    public SNSType convertToEntityAttribute(String dbData) {
        return SNSType.toConverter(dbData);
    }
}
