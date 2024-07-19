package com.tht.infra.user.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tht.enums.EntityState;
import com.tht.infra.user.QUser;
import com.tht.infra.user.QUserSns;
import com.tht.enums.user.SNSType;
import com.tht.infra.user.mapper.QUserSnsMapper;
import com.tht.infra.user.mapper.UserSnsMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class UserSNSRepositoryCustomImpl implements UserSNSRepositoryCustom {

    private static final QUserSns userSns = QUserSns.userSns;
    private static final QUser user = QUser.user;

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<List<UserSnsMapper>> findAllByPhoneNumber(final String phoneNumber) {
        return Optional.ofNullable(queryFactory.select(
                new QUserSnsMapper(
                    user.phoneNumber,
                    user.userUuid,
                    userSns.snsType.coalesce(SNSType.NORMAL)
                )
            )
            .from(user)
            .leftJoin(userSns).on(user.userUuid.eq(userSns.userUuid))
            .where(
                user.phoneNumber.eq(phoneNumber)
                    .and(user.state.eq(EntityState.ACTIVE))
            )
            .fetch());
    }

    @Override
    public Optional<String> findUserUuidBySnsIdKey(final SNSType snsType,
        final String snsUniqueId) {

        return Optional.ofNullable(
            queryFactory.select(
                    user.userUuid
                )
                .from(user)
                .leftJoin(userSns).on(user.userUuid.eq(userSns.userUuid))
                .where(userSns.snsType.eq(snsType)
                    .and(userSns.snsUniqueId.eq(snsUniqueId))
                )
                .fetchOne()
        );
    }

}
