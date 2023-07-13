package com.tht.api.app.fixture.main;

import com.tht.api.app.facade.main.response.TalkKeywordResponse;
import java.util.List;

public abstract class TalkKeywordFixture {

    private static final int idx = 1;
    private static final String keyword = "talk keyword";
    private static final int talkKeywordImgIdx = 2;

    public static TalkKeywordResponse make() {
        return new TalkKeywordResponse(
            idx, keyword, talkKeywordImgIdx
        );
    }

    public static List<TalkKeywordResponse> makeList() {
        return List.of(
            make(), make(), make()
        );
    }
}
