package com.tht.domain.entity.dailyfalling;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDailyFallingException extends RuntimeException {

    public UserDailyFallingException(final String message) {
        super(message);
    }

    public static UserDailyFallingException notChoice() {
        return new UserDailyFallingException("오늘의 폴링 주제어를 선택하지 않았거나, 유효하지 않은 주제어를 선택하였습니다.");
    }

    public static UserDailyFallingException alreadyChoice() {
        return new UserDailyFallingException("오늘의 폴링 주제어를 이미 선택하셨습니다.");
    }
}
