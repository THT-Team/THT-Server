package com.tht.api.app.fixture.main;

import com.tht.api.app.facade.main.response.DailyFallingResponse;
import java.util.List;

public class DailyFallingResponseFixture {


    private static final long idx = 1;
    private static final String keyword = "키워드";
    private static final String keywordImgUrl = "카워드-이미지-url";
    private static final String talkIssue = "키워드 파생질문 - 이런이런 주제로 이야기해볼까요~";

    public static DailyFallingResponse make() {
        return new DailyFallingResponse(idx, keyword, keywordImgUrl, talkIssue);
    }

    public static List<DailyFallingResponse> makeList() {
        return List.of(make());
    }
}
