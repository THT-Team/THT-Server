package com.tht.thtcommonutils.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class UnixTimeUtils {

    public static long convertUnixTimeForUTC(final LocalDateTime localDateTime) {

        ZoneId zoneId = ZoneId.systemDefault();
        return localDateTime.atZone(zoneId).toEpochSecond();
    }
}
