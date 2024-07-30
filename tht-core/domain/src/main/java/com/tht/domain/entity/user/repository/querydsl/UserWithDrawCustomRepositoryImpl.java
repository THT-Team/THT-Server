package com.tht.domain.entity.user.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tht.domain.entity.user.QUser;
import com.tht.domain.entity.user.QUserWithDrawLog;
import com.tht.domain.entity.user.repository.querydsl.mapper.QUserWithDrawLogMapper;
import com.tht.domain.entity.user.repository.querydsl.mapper.UserWithDrawLogMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class UserWithDrawCustomRepositoryImpl implements UserWithDrawCustomRepository {

    private static final QUser user  = QUser.user;
    private static final QUserWithDrawLog userWithDrawLog = QUserWithDrawLog.userWithDrawLog;

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<UserWithDrawLogMapper> findAllWithDrawLogList(Pageable pageable) {

        final long count = queryFactory.select(userWithDrawLog.idx)
            .from(userWithDrawLog)
            .stream()
            .count();

        final List<UserWithDrawLogMapper> result = queryFactory.select(
                new QUserWithDrawLogMapper(
                    userWithDrawLog.userUuid,
                    user.username,
                    userWithDrawLog.reason,
                    userWithDrawLog.feedBack,
                    user.state,
                    userWithDrawLog.createdAt
                )
            ).from(userWithDrawLog)
            .innerJoin(user).on(user.userUuid.eq(userWithDrawLog.userUuid))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        return new PageImpl<>(result, pageable, count);
    }
}
