package com.tht.api.app.repository.user.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tht.api.app.entity.enums.EntityState;
import com.tht.api.app.entity.enums.SNSType;
import com.tht.api.app.entity.user.QUser;
import com.tht.api.app.entity.user.QUserSns;
import com.tht.api.app.repository.mapper.QUserSnsMapper;
import com.tht.api.app.repository.mapper.UserSnsMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

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
