package com.tht.domain.entity.user.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tht.domain.entity.idealtype.IdealTypeMapper;
import com.tht.domain.entity.idealtype.QIdealType;
import com.tht.domain.entity.idealtype.QIdealTypeMapper;
import com.tht.domain.entity.user.QUserIdealType;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class UserIdealTypeCustomRepositoryImpl implements UserIdealTypeCustomRepository {

    private static final QIdealType idealType = QIdealType.idealType;
    private static final QUserIdealType userIdealType = QUserIdealType.userIdealType;
    private final JPAQueryFactory queryFactory;

    @Override
    public List<IdealTypeMapper> findIdealInfoBy(final String userUuid) {
        return queryFactory.select(
                new QIdealTypeMapper(
                    idealType.idx,
                    idealType.name,
                    idealType.emojiCode
                )
            )
            .from(userIdealType)
            .innerJoin(idealType).on(idealType.idx.eq(userIdealType.idealTypeIdx))
            .where(userIdealType.userUuid.eq(userUuid))
            .fetch();
    }
}
