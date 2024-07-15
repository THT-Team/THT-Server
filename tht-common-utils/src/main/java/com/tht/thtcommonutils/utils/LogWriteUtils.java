package com.tht.thtcommonutils.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LogWriteUtils {

    private LogWriteUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static void logInfo(final String message) {
        log.info(message);
    }

    public static void createInfo(final Object entity) {
        log.info(String.format("{"
            + "method : create, "
            + "entity_name : %s, "
            + "params : %s"
            + "}", entity.getClass().getSimpleName(), entity)
        );
    }

    public static void logError(final String message){
        log.error(message);
    }

    public static void logWarn(final String message) {
        log.warn(message);
    }
}
