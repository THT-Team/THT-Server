package com.tht.api.app.repository.meta.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tht.api.app.entity.meta.DailyFallingActiveInfo;
import java.time.LocalDateTime;
import java.util.Optional;

import com.tht.api.app.entity.meta.QDailyFallingActiveInfo;
import lombok.RequiredArgsConstructor;

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
