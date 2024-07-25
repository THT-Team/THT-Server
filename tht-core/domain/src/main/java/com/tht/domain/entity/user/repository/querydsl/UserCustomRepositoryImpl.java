package com.tht.domain.entity.user.repository.querydsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tht.domain.entity.user.QUser;
import com.tht.domain.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
public class UserCustomRepositoryImpl implements UserCustomRepository{

    private static final QUser user = QUser.user;

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<User> getUserListForPage(final String search, final Pageable pageable) {

        final List<User> results = queryFactory
            .selectFrom(user)
            .where(searchText(search))
            .orderBy(user.createdAt.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        long count = queryFactory.select(user.idx).from(user).stream().count();

        return new PageImpl<>(results, pageable, count);
    }

    private static BooleanExpression searchText(final String search) {

        if (Objects.isNull(search) || search.isEmpty()) {
            return null;
        }
        return user.username.contains(search);
    }
}
