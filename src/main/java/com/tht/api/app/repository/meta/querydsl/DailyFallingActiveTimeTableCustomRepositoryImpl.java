package com.tht.api.app.repository.meta.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tht.api.app.entity.meta.DailyFallingActiveTimeTable;
import com.tht.api.app.entity.meta.QDailyFallingActiveTimeTable;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DailyFallingActiveTimeTableCustomRepositoryImpl implements
    DailyFallingActiveTimeTableCustomRepository {

    private static final QDailyFallingActiveTimeTable dailyFallingActiveTimeTable = QDailyFallingActiveTimeTable.dailyFallingActiveTimeTable;

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<DailyFallingActiveTimeTable> findByActiveNow() {

        final LocalDateTime now = LocalDateTime.now();

        return Optional.ofNullable(
            queryFactory.selectFrom(dailyFallingActiveTimeTable)
                .where(dailyFallingActiveTimeTable.startDateTime.loe(now)
                    .and(dailyFallingActiveTimeTable.endDateTime.gt(now)))
                .fetchOne()
        );
    }

}
