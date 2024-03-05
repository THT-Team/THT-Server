package com.tht.api.app.config;

import java.time.format.DateTimeFormatter;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomDateFormatUtils {

    private static DateTimeFormatter dateTimeInstance;
    private static DateTimeFormatter dateInstance;
    private static DateTimeFormatter noHyphenDateInstance;

    public static DateTimeFormatter getDateTimeInstance() {
        if (Objects.isNull(dateTimeInstance)) {
            dateTimeInstance = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        }
        return dateTimeInstance;
    }

    public static DateTimeFormatter getDotDateInstance() {
        if (Objects.isNull(dateInstance)) {
            dateInstance = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        }
        return dateInstance;
    }

    public static DateTimeFormatter getNoHyphenDateInstance() {
        if (Objects.isNull(noHyphenDateInstance)) {
            noHyphenDateInstance = DateTimeFormatter.ofPattern("yyyyMMdd");
        }
        return noHyphenDateInstance;
    }
}
