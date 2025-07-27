package com.tht.thtcommonutils.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConvertAgeUtils {

    public static int covertBeforeBirthAge(final Instant birthDay) {
        final LocalDate birthLocalDate = birthDay.atZone(ZoneId.systemDefault()).toLocalDate();
        final LocalDate now = LocalDate.now();

        int age = now.getYear() - birthLocalDate.getYear();

        if (now.getDayOfYear() < birthLocalDate.getDayOfYear()) {
            age--;
        }

        return age;
    }
}
