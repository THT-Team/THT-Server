package com.tht.api.app.facade.main.response;

import com.tht.api.app.entity.meta.TalkKeyword;

public record TalkKeywordResponse(
    int idx,
    String keyword,
    int keywordImgIdx
) {

    public static TalkKeywordResponse of(final TalkKeyword entity) {
        return new TalkKeywordResponse(
            entity.getIdx(),
            entity.getKeyword(),
            entity.getTalkKeywordImgIdx()
        );
    }
}

