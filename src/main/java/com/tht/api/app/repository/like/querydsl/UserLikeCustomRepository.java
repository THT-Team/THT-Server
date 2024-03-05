package com.tht.api.app.repository.like.querydsl;

import com.tht.api.app.repository.mapper.LikeReceiveMapper;
import java.util.List;

public interface UserLikeCustomRepository {

    List<LikeReceiveMapper> findReceivedLikes(final String userUuid,  final int size,
        final Long dailyFallingIdx, final Long likeIdx);
}
