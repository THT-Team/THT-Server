package com.tht.domain.entity.like;


import java.util.List;

public interface UserLikeCustomRepository {

    List<LikeReceiveMapper> findReceivedLikes(final String userUuid, final int size,
                                              final Long dailyFallingIdx, final Long likeIdx);
}
