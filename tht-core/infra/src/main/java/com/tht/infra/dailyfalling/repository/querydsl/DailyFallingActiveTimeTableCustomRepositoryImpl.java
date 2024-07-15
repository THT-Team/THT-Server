package com.tht.infra.dailyfalling.repository.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tht.infra.dailyfalling.DailyFallingActiveInfo;
import com.tht.infra.dailyfalling.QDailyFallingActiveInfo;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
public class DailyFallingActiveTimeTableCustomRepositoryImpl implements
    DailyFallingActiveTimeTableCustomRepository {

    private static final QDailyFallingActiveInfo dailyFallingActiveInfo = QDailyFallingActiveInfo.dailyFallingActiveInfo;

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<DailyFallingActiveInfo> findByActiveNow() {

        final LocalDateTime now = LocalDateTime.now();

        return Optional.ofNullable(
            queryFactory.selectFrom(dailyFallingActiveInfo)
                .where(dailyFallingActiveInfo.startDateTime.loe(now)
                    .and(dailyFallingActiveInfo.endDateTime.gt(now)))
                .fetchOne()
        );
    }

}
