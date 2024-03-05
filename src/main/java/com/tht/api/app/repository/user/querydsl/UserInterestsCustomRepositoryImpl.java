package com.tht.api.app.repository.user.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tht.api.app.entity.meta.QInterest;
import com.tht.api.app.entity.user.QUserInterests;
import com.tht.api.app.repository.mapper.InterestMapper;
import com.tht.api.app.repository.mapper.QInterestMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserInterestsCustomRepositoryImpl implements UserInterestsCustomRepository {

    private static final QUserInterests userInterests = QUserInterests.userInterests;
    private static final QInterest interests = QInterest.interest;
    private final JPAQueryFactory queryFactory;

    @Override
    public List<InterestMapper> findInterestInfoBy(String userUuid) {
        return queryFactory.select(
                new QInterestMapper(
                    interests.idx,
                    interests.name,
                    interests.emojiCode
                )
            )
            .from(userInterests)
            .innerJoin(interests).on(interests.idx.eq(userInterests.interestIdx))
            .where(userInterests.userUuid.eq(userUuid))
            .fetch();
    }
}
