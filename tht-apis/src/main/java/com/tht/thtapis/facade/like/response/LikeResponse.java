package com.tht.thtapis.facade.like.response;

public record LikeResponse(boolean isMatching, Long chatRoomIdx) {

    public static LikeResponse nonMatching() {
        return new LikeResponse(false, null);
    }

    public static LikeResponse matching(final long chatRoomIdx) {
        return new LikeResponse(true, chatRoomIdx);
    }
}
