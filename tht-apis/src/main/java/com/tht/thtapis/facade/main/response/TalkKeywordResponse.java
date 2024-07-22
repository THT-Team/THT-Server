package com.tht.thtapis.facade.main.response;


import com.tht.domain.entity.talkkeyword.TalkKeyword;

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

