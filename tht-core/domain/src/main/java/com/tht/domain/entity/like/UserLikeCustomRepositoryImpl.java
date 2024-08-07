package com.tht.domain.entity.like;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tht.enums.EntityState;
import com.tht.enums.user.LikeState;
import com.tht.domain.entity.dailyfalling.QDailyFalling;
import com.tht.domain.entity.talkkeyword.QTalkKeyword;
import com.tht.domain.entity.user.QUser;
import com.tht.domain.entity.user.QUserLocationInfo;
import com.tht.domain.entity.user.QUserProfilePhoto;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class UserLikeCustomRepositoryImpl implements UserLikeCustomRepository {

    private static final QUserLike userLike = QUserLike.userLike;
    private static final QUser user = QUser.user;
    private static final QUserLocationInfo userLocationInfo = QUserLocationInfo.userLocationInfo;
    private static final QUserProfilePhoto userProfilePhoto = QUserProfilePhoto.userProfilePhoto;

    private static final QDailyFalling dailyFalling = QDailyFalling.dailyFalling;
    private static final QTalkKeyword talkKeyword = QTalkKeyword.talkKeyword;

    private final JPAQueryFactory queryFactory;

    private static final int FIRST_PROFILE = 1;
    @Override
    public List<LikeReceiveMapper> findReceivedLikes(final String userUuid, final int size,
                                                     final Long dailyFallingIdx, final Long likeIdx) {

        return queryFactory
            .select(
                new QLikeReceiveMapper(
                    dailyFalling.idx,
                    userLike.idx,
                    talkKeyword.keyword,
                    dailyFalling.talkIssue,
                    user.userUuid,
                    user.username,
                    userProfilePhoto.url,
                    user.birthDay,
                    userLocationInfo.address,
                    userLike.createdAt
                )
            )
            .from(userLike)
            .innerJoin(user)
            .on(userLike.userUuid.eq(user.userUuid)
                .and(user.state.eq(EntityState.ACTIVE))
                .and(userLike.targetUserUuid.eq(userUuid))
            )
            .innerJoin(userLocationInfo).on(user.userUuid.eq(userLocationInfo.userUuid))
            .innerJoin(userProfilePhoto)
            .on(user.userUuid.eq(userProfilePhoto.userUuid)
                .and(userProfilePhoto.priority.eq(FIRST_PROFILE)))
            .innerJoin(dailyFalling).on(userLike.dailyFallingIdx.eq(dailyFalling.idx))
            .innerJoin(talkKeyword).on(dailyFalling.talkKeywordIdx.eq(talkKeyword.idx))
            .where(
                userLike.likeState.eq(LikeState.LIKE),
                userLike.state.eq(EntityState.ACTIVE),
                equalsAndLessThaDailyFallingIdx(dailyFallingIdx),
                lessThanLikeIdx(likeIdx)
            )
            .orderBy(
                userLike.dailyFallingIdx.desc(),
                userLike.createdAt.desc()
            )
            .limit(size)
            .fetch();
    }

    private BooleanExpression lessThanLikeIdx(final Long likeIdx) {
        if (Objects.isNull(likeIdx)) {
            return null;
        }

        return userLike.idx.lt(likeIdx);
    }

    private BooleanExpression equalsAndLessThaDailyFallingIdx(final Long dailyFallingIdx) {
        if (Objects.isNull(dailyFallingIdx)) {
            return null;
        }
        return userLike.dailyFallingIdx.loe(dailyFallingIdx);
    }

}
