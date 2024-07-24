package com.tht.enums.inquiry;

import jakarta.persistence.AttributeConverter;

import java.util.Objects;

public class InquiryStatusConverter implements AttributeConverter<InquiryStatus, String> {

    @Override
    public String convertToDatabaseColumn(InquiryStatus inquiryStatus) {
        if (Objects.isNull(inquiryStatus)) {
            throw new NullPointerException("Enum Converting String - DailyFallingType is null");
        }

        return inquiryStatus.getKey();
    }

    @Override
    public InquiryStatus convertToEntityAttribute(String dbData) {
        return InquiryStatus.toConverter(dbData);
    }
}
