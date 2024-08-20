package com.tht.domain.entity.report.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tht.domain.entity.report.QUserReport;
import com.tht.domain.entity.report.repository.mapper.QUserReportMapper;
import com.tht.domain.entity.report.repository.mapper.UserReportMapper;
import com.tht.domain.entity.user.QUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class UserReportCustomRepositoryImpl implements UserReportCustomRepository {

    private static final QUserReport userReport = QUserReport.userReport;
    private static final QUser user = QUser.user;

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<UserReportMapper> findAllReportList(final Pageable pageable) {

        final QUser reportedUser = new QUser("reportedUser");

        final long totalCount = queryFactory.select(userReport.idx)
            .from(userReport)
            .stream().count();

        final List<UserReportMapper> result = queryFactory.select(
                new QUserReportMapper(
                    userReport.reportUserUuid,
                    user.username,
                    user.gender,
                    user.preferGender,
                    userReport.createdAt,
                    user.state,
                    reportedUser.username,
                    userReport.reason
                )
            ).from(userReport)
            .innerJoin(user).on(user.userUuid.eq(userReport.reportUserUuid))
            .innerJoin(reportedUser).on(reportedUser.userUuid.eq(userReport.userUuid))
            .orderBy(userReport.idx.desc())
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        return new PageImpl<>(result, pageable, totalCount);
    }
}
