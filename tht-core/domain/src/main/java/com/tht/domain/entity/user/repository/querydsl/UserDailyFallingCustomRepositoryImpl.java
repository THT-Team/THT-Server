package com.tht.domain.entity.user.repository.querydsl;

import com.querydsl.core.Tuple;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tht.domain.entity.block.QUserBlock;
import com.tht.enums.EntityState;
import com.tht.domain.entity.dailyfalling.QDailyFalling;
import com.tht.domain.entity.dailyfalling.QDailyFallingActiveInfo;
import com.tht.domain.entity.dailyfalling.QUserDailyFalling;
import com.tht.domain.entity.dailyfalling.mapper.DailyFallingTimeMapper;
import com.tht.domain.entity.dailyfalling.mapper.QDailyFallingTimeMapper;
import com.tht.domain.entity.dailyfalling.mapper.QUserDailyFallingMapper;
import com.tht.domain.entity.dailyfalling.mapper.UserDailyFallingMapper;
import com.tht.domain.entity.user.mapper.MainScreenUserInfoMapper;
import com.tht.domain.entity.idealtype.QIdealType;
import com.tht.domain.entity.idealtype.QIdealTypeMapper;
import com.tht.domain.entity.interesst.QInterest;
import com.tht.domain.entity.interesst.QInterestMapper;
import com.tht.domain.entity.like.QUserLike;
import com.tht.domain.entity.user.*;
import com.tht.enums.user.Gender;
import com.tht.enums.user.LikeState;
import com.tht.domain.entity.user.mapper.QMainScreenUserInfoMapper;
import com.tht.domain.entity.user.mapper.QUserProfilePhotoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
public class UserDailyFallingCustomRepositoryImpl implements UserDailyFallingCustomRepository {

    private static final int DISLIKE_HOLDING_DAY = 2;

    private static final QUserDailyFalling userDailyFalling = QUserDailyFalling.userDailyFalling;
    private static final QDailyFalling dailyFalling = QDailyFalling.dailyFalling;
    private static final QUser user = QUser.user;
    private static final QUserInterests userInterests = QUserInterests.userInterests;
    private static final QUserIdealType userIdealType = QUserIdealType.userIdealType;
    private static final QUserProfilePhoto userProfilePhoto = QUserProfilePhoto.userProfilePhoto;
    private static final QUserLocationInfo userLocationInfo = QUserLocationInfo.userLocationInfo;
    private static final QInterest interest = QInterest.interest;
    private static final QIdealType idealType = QIdealType.idealType;
    private static final QDailyFallingActiveInfo dailyFallingActiveInfo = QDailyFallingActiveInfo.dailyFallingActiveInfo;
    private static final QUserFriend userFriend = QUserFriend.userFriend;
    private static final QUserBlock userBlock = QUserBlock.userBlock;
    private static final QUserLike userLike = QUserLike.userLike;

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<UserDailyFallingMapper> findByUserChoosingToDayFalling(final String userUuid) {

        return Optional.ofNullable(queryFactory.select(
                new QUserDailyFallingMapper(
                    userDailyFalling.idx,
                    userDailyFalling.userUuid,
                    userDailyFalling.dailyFallingIdx
                )
            )
            .from(userDailyFalling)
            .innerJoin(dailyFalling)
            .on(userDailyFalling.dailyFallingIdx.eq(dailyFalling.idx)
                .and(dailyFalling.state.eq(EntityState.ACTIVE)))
            .innerJoin(dailyFallingActiveInfo)
            .on(dailyFalling.activeTimeTableIdx.eq(dailyFallingActiveInfo.idx))
            .where(
                userDailyFalling.userUuid.eq(userUuid)
                    .and(userDailyFalling.state.eq(EntityState.ACTIVE))
                , activeTimeAtNow()
            )
            .orderBy(userDailyFalling.createdAt.desc())
            .fetchFirst());
    }

    @Override
    public List<MainScreenUserInfoMapper> findAllMatchingFallingUser(
        final Long dailyFallingIdx, final Long userDailyFallingCourserIdx, final String myUuid,
        final Gender myGender, final Gender myPreferGender, Integer size) {

        size = Objects.isNull(size) ? 100 : size;

        final List<String> userFriendBlockUuidList = queryFactory
            .select(user.userUuid)
            .from(userFriend)
            .innerJoin(user).on(userFriend.phoneNumber.eq(user.phoneNumber))
            .where(userFriend.userUuid.eq(myUuid)
                .and(userFriend.state.eq(EntityState.ACTIVE)))
            .fetch();

        final LocalDateTime disLikeDisableTime = LocalDateTime.now().minusDays(DISLIKE_HOLDING_DAY);

        final List<String> userDisLikeList = new ArrayList<>();

        final List<Tuple> tuples = queryFactory
            .select(userLike.userUuid, userLike.targetUserUuid)
            .from(userLike)
            .where(
                userLike.lastModifiedAt.goe(disLikeDisableTime),
                userLike.likeState.eq(LikeState.DISLIKE),
                userLike.userUuid.eq(myUuid).or(userLike.targetUserUuid.eq(myUuid))
            )
            .fetch();

        for (Tuple t : tuples) {
            userDisLikeList.add(t.get(userLike.userUuid));
            userDisLikeList.add(t.get(userLike.targetUserUuid));
        }

        final List<String> distinctUserDisLikeList = userDisLikeList.stream().distinct().toList();

        final List<Long> userDailyFallingIdxList = queryFactory
            .select(userDailyFalling.idx)
            .from(userDailyFalling)
            .leftJoin(userBlock)
            .on(
                userDailyFalling.dailyFallingIdx.eq(dailyFallingIdx),
                userDailyFalling.state.eq(EntityState.ACTIVE),
                userBlock.userUuid.eq(myUuid),
                userDailyFalling.userUuid.eq(userBlock.blockUserUuid)
            )
            .innerJoin(user)
            .on(
                user.userUuid.eq(userDailyFalling.userUuid),
                user.state.eq(EntityState.ACTIVE)
            )
            .leftJoin(userLike)
            .on(userDailyFalling.userUuid.eq(userLike.targetUserUuid)
                .and(userDailyFalling.dailyFallingIdx.eq(userLike.dailyFallingIdx))
                .and(userLike.userUuid.eq(myUuid))
            )
            .where(
                userBlock.idx.isNull(),
                userDailyFalling.dailyFallingIdx.eq(dailyFallingIdx),
                userDailyFalling.state.eq(EntityState.ACTIVE),
                userDailyFalling.userUuid.ne(myUuid),
                filterGender(myGender, myPreferGender),
                filterAlreadyLike(),
                notInUserIdx(userFriendBlockUuidList),
                notInUserIdx(distinctUserDisLikeList),
                moreThan(userDailyFallingCourserIdx)
            )
            .limit(size)
            .fetch();

        if (CollectionUtils.isEmpty(userDailyFallingIdxList)) {
            return new ArrayList<>();
        }

        return queryFactory.selectFrom(userDailyFalling)
            .innerJoin(user)
            .on(userDailyFalling.userUuid.eq(user.userUuid)
                .and(userDailyFalling.state.eq(EntityState.ACTIVE))
                .and(user.state.eq(EntityState.ACTIVE))
                .and(userDailyFalling.userUuid.ne(myUuid))
            )
            .innerJoin(userLocationInfo)
            .on(userDailyFalling.userUuid.eq(userLocationInfo.userUuid))
            .innerJoin(userProfilePhoto)
            .on(userDailyFalling.userUuid.eq(userProfilePhoto.userUuid))
            .innerJoin(userInterests)
            .on(userDailyFalling.userUuid.eq(userInterests.userUuid))
            .innerJoin(interest)
            .on(userInterests.interestIdx.eq(interest.idx))
            .innerJoin(userIdealType)
            .on(userDailyFalling.userUuid.eq(userIdealType.userUuid))
            .innerJoin(idealType)
            .on(userIdealType.idealTypeIdx.eq(idealType.idx))
            .where(
                userDailyFalling.idx.in(userDailyFallingIdxList)
            )
            .transform(GroupBy.groupBy(user.userUuid).list(
                new QMainScreenUserInfoMapper(
                    user.username,
                    user.userUuid,
                    user.birthDay,
                    userLocationInfo.address,
                    user.smoking,
                    user.drinking,
                    user.religion,
                    GroupBy.set(
                        new QIdealTypeMapper(
                            idealType.idx,
                            idealType.name,
                            idealType.emojiCode
                        )
                    ),
                    GroupBy.set(
                        new QInterestMapper(
                            interest.idx,
                            interest.name,
                            interest.emojiCode
                        )
                    ),
                    GroupBy.set(
                        new QUserProfilePhotoMapper(
                            userProfilePhoto.idx,
                            userProfilePhoto.userUuid,
                            userProfilePhoto.url,
                            userProfilePhoto.priority
                        )
                    ),
                    user.introduction,
                    userDailyFalling.idx,
                    userLocationInfo.lat,
                    userLocationInfo.lon
                )));
    }

    @Override
    public long countByDailyFallingId(Long dailyFallingIdx) {
        return queryFactory.select(userDailyFalling.idx)
            .from(userDailyFalling)
            .where(userDailyFalling.dailyFallingIdx.eq(dailyFallingIdx))
            .stream()
            .count();
    }

    private BooleanExpression filterGender(Gender myGender, Gender myPreferGender) {

        if (myPreferGender.equals(Gender.BISEXUAL)) {
            return user.preferGender.eq(Gender.BISEXUAL).or(user.preferGender.eq(myGender));
        }

        return user.gender.eq(myPreferGender)
            .and(user.preferGender.eq(myGender).or(user.preferGender.eq(Gender.BISEXUAL)));
    }

    private BooleanExpression filterAlreadyLike() {
        return userLike.idx.isNull();
    }

    @Override
    public Optional<DailyFallingTimeMapper> findFallingTimeInfo(final String userUuid) {

        return Optional.ofNullable(queryFactory.select(
                new QDailyFallingTimeMapper(
                    dailyFalling.idx,
                    dailyFallingActiveInfo.endDateTime
                )
            ).from(userDailyFalling)
            .innerJoin(dailyFalling)
            .on(dailyFalling.idx.eq(userDailyFalling.dailyFallingIdx))
            .innerJoin(dailyFallingActiveInfo)
            .on(dailyFallingActiveInfo.idx.eq(dailyFalling.activeTimeTableIdx))
            .where(
                userDailyFalling.userUuid.eq(userUuid)
                    .and(userDailyFalling.state.eq(EntityState.ACTIVE))
                    .and(dailyFalling.state.eq(EntityState.ACTIVE))
                    .and(dailyFallingActiveInfo.state.eq(EntityState.ACTIVE)),
                activeTimeAtNow()
            )
            .fetchFirst());
    }

    private BooleanExpression moreThan(final Long userDailyFallingCourserIdx) {
        if (Objects.isNull(userDailyFallingCourserIdx)) {
            return null;
        }
        return userDailyFalling.idx.gt(userDailyFallingCourserIdx);
    }

    private BooleanExpression notInUserIdx(final List<String> userUuidList) {
        if (Objects.isNull(userUuidList)) {
            return null;
        }

        return userDailyFalling.userUuid.notIn(userUuidList);
    }

    private BooleanExpression activeTimeAtNow() {
        final LocalDateTime now = LocalDateTime.now();

        return dailyFallingActiveInfo.startDateTime.loe(now)
            .and(dailyFallingActiveInfo.endDateTime.gt(now));
    }
}
