package com.tht.api.app.entity.enums.converter;

import com.tht.api.app.entity.enums.AgreementCategory;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Objects;

@Converter
public class AgreementCategoryConverter implements AttributeConverter<AgreementCategory, String> {

    @Override
    public String convertToDatabaseColumn(final AgreementCategory attribute) {
        if (Objects.isNull(attribute)) {
            throw new NullPointerException("Enum Converting String - AgreementCategory is null");
        }

        return attribute.name();
    }

    @Override
    public AgreementCategory convertToEntityAttribute(final String dbData) {
          return AgreementCategory.toConverter(dbData);
    }
}
