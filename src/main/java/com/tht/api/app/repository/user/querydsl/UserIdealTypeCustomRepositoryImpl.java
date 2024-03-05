package com.tht.api.app.repository.user.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tht.api.app.entity.meta.QIdealType;
import com.tht.api.app.entity.user.QUserIdealType;
import com.tht.api.app.repository.mapper.IdealTypeMapper;
import com.tht.api.app.repository.mapper.QIdealTypeMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;

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
