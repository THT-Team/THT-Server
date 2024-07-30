package com.tht.domain.entity.block.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tht.domain.entity.block.QUserBlock;
import com.tht.domain.entity.block.repository.mapper.QUserBlockMapper;
import com.tht.domain.entity.block.repository.mapper.UserBlockMapper;
import com.tht.domain.entity.user.QUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class UserBlockCustomRepositoryImpl implements UserBlockCustomRepository {

    private static final QUserBlock userBlock = QUserBlock.userBlock;
    private static final QUser user = QUser.user;

    private final JPAQueryFactory queueFactory;

    @Override
    public Page<UserBlockMapper> findAllBlockList(Pageable pageable) {

        final QUser blockedUser = QUser.user;

        final long totalCount = queueFactory.select(userBlock.idx)
            .from(userBlock)
            .stream().count();

        final List<UserBlockMapper> result = queueFactory.select(
                new QUserBlockMapper(
                    userBlock.blockUserUuid,
                    user.username,
                    user.gender,
                    user.state,
                    userBlock.createdAt,
                    blockedUser.username
                )
            ).from(userBlock)
            .innerJoin(user).on(user.userUuid.eq(userBlock.blockUserUuid))
            .innerJoin(blockedUser).on(blockedUser.userUuid.eq(userBlock.userUuid))
            .orderBy(userBlock.idx.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        return new PageImpl<>(result, pageable, totalCount);
    }
}
