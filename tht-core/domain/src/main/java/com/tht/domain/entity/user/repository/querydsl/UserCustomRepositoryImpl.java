package com.tht.domain.entity.user.repository.querydsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tht.domain.entity.idealtype.QIdealType;
import com.tht.domain.entity.interesst.QInterest;
import com.tht.domain.entity.user.*;
import com.tht.domain.entity.user.mapper.QUserListMapper;
import com.tht.domain.entity.user.mapper.UserListMapper;
import com.tht.domain.entity.user.repository.querydsl.mapper.QUserDetailMapper;
import com.tht.domain.entity.user.repository.querydsl.mapper.UserDetailMapper;
import com.tht.enums.user.SNSType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class UserCustomRepositoryImpl implements UserCustomRepository{

    private static final QUser user = QUser.user;
    private static final QUserSns userSns = QUserSns.userSns;
    private static final QUserAgreement userAgreement = QUserAgreement.userAgreement;
    private static final QUserLocationInfo userLocationInfo = QUserLocationInfo.userLocationInfo;
    private static final QUserProfilePhoto userProfilePhoto = QUserProfilePhoto.userProfilePhoto;
    private static final QUserInterests userInterests = QUserInterests.userInterests;
    private static final QUserIdealType userIdealType = QUserIdealType.userIdealType;
    private static final QInterest interest = QInterest.interest;
    private static final QIdealType idealType = QIdealType.idealType;

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<UserListMapper> getUserListForPage(final String search, final Pageable pageable) {

        final int profile = 1;

        final List<UserListMapper> results = queryFactory
            .select(new QUserListMapper(
                user.username,
                userProfilePhoto.url,
                user.userUuid,
                user.createdAt,
                user.state
            ))
            .from(user)
            .innerJoin(userProfilePhoto)
            .on(userProfilePhoto.userUuid.eq(user.userUuid).and(userProfilePhoto.priority.eq(profile)))
            .where(searchText(search))
            .orderBy(user.createdAt.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        long count = queryFactory.select(user.idx)
            .from(user)
            .where(searchText(search))
            .stream().count();

        return new PageImpl<>(results, pageable, count);
    }

    @Override
    public List<UserDetailMapper> getDetailInfo(final String userUuid) {

        return queryFactory.select(
                new QUserDetailMapper(
                    user.phoneNumber,
                    user.username,
                    user.birthDay,
                    user.email,
                    userSns.snsType.coalesce(SNSType.NORMAL).as("sns_type"),
                    userAgreement,
                    user.gender,
                    user.preferGender,
                    userProfilePhoto,
                    user.tall,
                    user.drinking,
                    user.religion,
                    user.smoking,
                    userLocationInfo.address,
                    interest.name,
                    idealType.name
                )
            )
            .from(user)
            .innerJoin(userAgreement).on(userAgreement.userUuid.eq(user.userUuid))
            .innerJoin(userLocationInfo).on(userLocationInfo.userUuid.eq(user.userUuid))
            .leftJoin(userSns).on(userSns.userUuid.eq(user.userUuid))
            .leftJoin(userProfilePhoto).on(userProfilePhoto.userUuid.eq(user.userUuid))
            .leftJoin(userIdealType).on(userIdealType.userUuid.eq(user.userUuid))
            .leftJoin(userInterests).on(userInterests.userUuid.eq(user.userUuid))
            .innerJoin(idealType).on(userIdealType.idealTypeIdx.eq(idealType.idx))
            .innerJoin(interest).on(userInterests.interestIdx.eq(interest.idx))
            .where(user.userUuid.eq(userUuid))
            .fetch()
            ;
    }

    private static BooleanExpression searchText(final String search) {

        if (Objects.isNull(search) || search.isEmpty()) {
            return null;
        }
        return user.username.contains(search);
    }
}
