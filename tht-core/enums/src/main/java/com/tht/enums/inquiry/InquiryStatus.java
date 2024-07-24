package com.tht.enums.inquiry;

import com.tht.enums.EnumModel;
import com.tht.enums.EnumStateNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum InquiryStatus implements EnumModel {

    RECEPTION("reception"),
    CHECK("check"),
    COMPLETED("completed");

    private final String value;

    public static InquiryStatus toConverter(final String dbData) {
        return Arrays.stream(InquiryStatus.values())
            .filter(inquiryStatus -> inquiryStatus.name().equals(dbData))
            .findAny()
            .orElseThrow(() -> EnumStateNotFoundException.ofInquiryStatus(dbData));
    }

    @Override
    public String getKey() {
        return name();
    }

    @Override
    public String getValue() {
        return value;
    }
}
