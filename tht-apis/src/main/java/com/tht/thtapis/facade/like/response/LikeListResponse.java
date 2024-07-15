package com.tht.thtapis.facade.like.response;

import java.util.List;

public record LikeListResponse(

    List<LikeReceiveResponse> likeList,
    int size,
    Long lastFallingTopicIdx,
    Long lastLikeIdx
) {

    public static LikeListResponse of(final List<LikeReceiveResponse> list) {

        if (list.isEmpty()) {
            return getEmpty();
        }

        final LikeReceiveResponse last = list.get(list.size() - 1);

        return new LikeListResponse(
            list,
            list.size(),
            last.dailyFallingIdx(),
            last.likeIdx()
        );
    }

    private static LikeListResponse getEmpty() {
        return new LikeListResponse(List.of(), 0, null, null);
    }
}
