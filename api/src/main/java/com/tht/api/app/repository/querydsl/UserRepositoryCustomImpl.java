package com.tht.api.app.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tht.api.app.entity.enums.SNSType;
import com.tht.api.app.entity.user.QUser;
import com.tht.api.app.entity.user.QUserSns;
import com.tht.api.app.repository.mapper.QUserSnsMapper;
import com.tht.api.app.repository.mapper.UserSnsMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

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
            .where(user.phoneNumber.eq(phoneNumber))
            .fetch());
    }

}