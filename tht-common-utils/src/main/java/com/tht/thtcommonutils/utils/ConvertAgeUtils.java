package com.tht.thtcommonutils.utils;

import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConvertAgeUtils {

    public static int covertBeforeBirthAge(final LocalDate birthDay) {
        final LocalDate today = LocalDate.now();
        int age = today.getYear() - birthDay.getYear();

        if (today.getMonthValue() < birthDay.getMonthValue() ||
            (today.getMonthValue() == birthDay.getMonthValue() && today.getDayOfMonth() < birthDay.getDayOfMonth())) {
            age--;
        }

        return age;
    }
}
