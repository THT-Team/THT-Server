package com.tht.thtapis.fixture.main;

import com.tht.enums.dailyfalling.DailyFallingType;
import com.tht.thtapis.facade.main.response.DailyFallingResponse;
import com.tht.thtapis.facade.main.response.DailyFallingTopicResponse;

import java.util.List;

public class DailyFallingResponseFixture {


    private static final long idx = 112304;
    private static final String keyword = "키워드";
    private static final int keywordIdx = 3;
    private static final String keywordImgUrl = "카워드-이미지-url";
    private static final String talkIssue = "키워드 파생질문 - 이런이런 주제로 이야기해볼까요~";
    private static final long expirationTime = 1687744800;
    private static final DailyFallingType type = DailyFallingType.ONE_CHOICE;
    private static final String introduction = "오늘 나는 너랑..";

    public static DailyFallingTopicResponse makeTopic() {
        return new DailyFallingTopicResponse(idx, keyword, keywordIdx, keywordImgUrl, talkIssue);
    }

    public static List<DailyFallingTopicResponse> makeTopicList() {
        return List.of(makeTopic());
    }

    public static DailyFallingResponse make() {
        return new DailyFallingResponse(expirationTime, type.getValue(), introduction, makeTopicList());
    }
}
