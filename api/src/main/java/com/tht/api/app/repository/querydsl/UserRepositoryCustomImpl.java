package com.tht.api.app.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tht.api.app.entity.user.QUserSns;
import com.tht.api.app.repository.mapper.UserSnsMapper;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    private static final QUserSns userSns = QUserSns.userSns;

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<List<UserSnsMapper>> findAllByPhoneNumber(final String phoneNumber) {
//        return queryFactory.select(
//            )
//            .from(userSns)
//            .fetch();
        return Optional.empty();
    }

}
