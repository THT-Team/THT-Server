package com.tht.api.app.utils;

import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConvertAgeUtils {

    public static int covertBeforeBirthAge(final LocalDate birthDay) {
        final int simpleAge = LocalDate.now().minusYears(birthDay.getYear()).getYear();

        return simpleAge - minusBeforeBirthDayAge(birthDay, simpleAge);
    }

    private static int minusBeforeBirthDayAge(final LocalDate birthDay, final int simpleAge) {

        final LocalDate now = LocalDate.now();

        if (birthDay.plusDays(simpleAge).isAfter(now)) {
            return 1;
        }
        return 0;
    }
}
