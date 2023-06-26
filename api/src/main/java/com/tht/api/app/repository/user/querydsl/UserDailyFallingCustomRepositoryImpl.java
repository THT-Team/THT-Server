package com.tht.api.app.repository.user.querydsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tht.api.app.entity.enums.EntityState;
import com.tht.api.app.entity.meta.QDailyFalling;
import com.tht.api.app.entity.meta.QIdealType;
import com.tht.api.app.entity.meta.QInterest;
import com.tht.api.app.entity.user.QUser;
import com.tht.api.app.entity.user.QUserDailyFalling;
import com.tht.api.app.entity.user.QUserIdealType;
import com.tht.api.app.entity.user.QUserInterests;
import com.tht.api.app.entity.user.QUserLocationInfo;
import com.tht.api.app.entity.user.QUserProfilePhoto;
import com.tht.api.app.repository.mapper.MainScreenUserInfoMapper;
import com.tht.api.app.repository.mapper.QIdealTypeMapper;
import com.tht.api.app.repository.mapper.QInterestMapper;
import com.tht.api.app.repository.mapper.QMainScreenUserInfoMapper;
import com.tht.api.app.repository.mapper.QUserDailyFallingMapper;
import com.tht.api.app.repository.mapper.QUserProfilePhotoMapper;
import com.tht.api.app.repository.mapper.UserDailyFallingMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.util.CollectionUtils;

@RequiredArgsConstructor
public class UserDailyFallingCustomRepositoryImpl implements UserDailyFallingCustomRepository {

    private static final QUserDailyFalling userDailyFalling = QUserDailyFalling.userDailyFalling;
    private static final QDailyFalling dailyFalling = QDailyFalling.dailyFalling;
    private static final QUser user = QUser.user;
    private static final QUserInterests userInterests = QUserInterests.userInterests;
    private static final QUserIdealType userIdealType = QUserIdealType.userIdealType;
    private static final QUserProfilePhoto userProfilePhoto = QUserProfilePhoto.userProfilePhoto;
    private static final QUserLocationInfo userLocationInfo = QUserLocationInfo.userLocationInfo;
    private static final QInterest interest = QInterest.interest;
    private static final QIdealType idealType = QIdealType.idealType;

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
            .where(userDailyFalling.state.eq(EntityState.ACTIVE)
//                .and(dailyFalling.activeDay.eq(LocalDate.now())) fixme.
            )
            .orderBy(userDailyFalling.createdAt.desc())
            .fetchFirst());
    }

    @Override
    public List<MainScreenUserInfoMapper> findAllMatchingFallingUser(final long dailyFallingIdx,
       final List<String> alreadySeenUserUuidList, final Long userDailyFallingCourserIdx,
        final String myUuid, Integer size) {

        size = Objects.isNull(size) ? 100 : size;

        List<Long> userDailyFallingIdxList = queryFactory
            .select(userDailyFalling.idx)
            .from(userDailyFalling)
            .where(
                userDailyFalling.dailyFallingIdx.eq(dailyFallingIdx),
                userDailyFalling.state.eq(EntityState.ACTIVE),
                userDailyFalling.userUuid.ne(myUuid),
                notInUserIdx(alreadySeenUserUuidList),
                moreThan(userDailyFallingCourserIdx)
            )
            .limit(size)
            .fetch();

        if (CollectionUtils.isEmpty(userDailyFallingIdxList)) {
            return new ArrayList<>();
        }

        return queryFactory.select(
                new QMainScreenUserInfoMapper(
                    user.username,
                    user.userUuid,
                    user.birthDay,
                    userLocationInfo.address,
                    new QIdealTypeMapper(
                        idealType.idx,
                        idealType.name,
                        idealType.emojiCode
                    ),
                    new QInterestMapper(
                        interest.idx,
                        interest.name,
                        interest.emojiCode
                    ),
                    new QUserProfilePhotoMapper(
                        userProfilePhoto.idx,
                        userProfilePhoto.userUuid,
                        userProfilePhoto.url,
                        userProfilePhoto.priority
                    ),
                    user.introduction,
                    userDailyFalling.idx
                )
            )
            .from(userDailyFalling)
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
            .fetch();
    }

    private BooleanExpression moreThan(final Long userDailyFallingCourserIdx) {
        if (Objects.isNull(userDailyFallingCourserIdx)) {
            return null;
        }
        return userDailyFalling.idx.gt(userDailyFallingCourserIdx);
    }

    private BooleanExpression notInUserIdx(final List<String> alreadySeenUserUuidList) {
        if(Objects.isNull(alreadySeenUserUuidList)){
            return null;
        }

        return userDailyFalling.userUuid.notIn(alreadySeenUserUuidList);
    }
}
