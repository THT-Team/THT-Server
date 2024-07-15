package com.tht.infra.dailyfalling;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DailyFallingException extends RuntimeException {

    public DailyFallingException(final String message) {
        super(message);
    }

    public static DailyFallingException notExist() {
        return new DailyFallingException("오늘의 폴링 주제어가 존재하지 않습니다.");
    }

}
