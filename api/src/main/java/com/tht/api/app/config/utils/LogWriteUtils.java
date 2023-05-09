package com.tht.api.app.config.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogWriteUtils {

    private LogWriteUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static void logInfo(final String message) {
        log.info(message);
    }

    public static void logError(final String message){
        log.error(message);
    }

    public static void logWarn(final String message) {
        log.warn(message);
    }
}
