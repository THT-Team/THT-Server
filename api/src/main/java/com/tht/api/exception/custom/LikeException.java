package com.tht.api.exception.custom;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LikeException extends RuntimeException{

    private LikeException(final String message) {
        super(message);
        log.error(message);
    }

    public static LikeException didNotMatchReceiver(final String userUuid, final long likeIdx) {
        return new LikeException(
            String.format("user uuid : %s 인 유저는 %s 래코드 번호의 좋아요를 받은 유저가 아닙니다.", userUuid, likeIdx)
        );
    }

    public static LikeException notFound(long likeIdx) {
        return new LikeException(likeIdx + " 레코드 번호의 좋아요 데이터를 찾을 수 없습니다.");
    }

    public static LikeException didNotWaitState() {
        return new LikeException("잘못된 요청입니다. 대기 상태의 좋아요가 아닙니다.");
    }
}
