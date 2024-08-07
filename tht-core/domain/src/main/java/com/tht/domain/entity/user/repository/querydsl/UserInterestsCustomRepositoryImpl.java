package com.tht.domain.entity.user.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tht.domain.entity.interesst.InterestMapper;

import com.tht.domain.entity.interesst.QInterest;
import com.tht.domain.entity.interesst.QInterestMapper;
import com.tht.domain.entity.user.QUserInterests;
import lombok.RequiredArgsConstructor;

import java.util.List;

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
