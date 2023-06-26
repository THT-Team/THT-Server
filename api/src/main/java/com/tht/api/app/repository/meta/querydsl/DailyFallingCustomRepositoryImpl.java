package com.tht.api.app.repository.meta.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tht.api.app.entity.meta.QDailyFalling;
import com.tht.api.app.entity.meta.QDailyFallingActiveTimeTable;
import com.tht.api.app.entity.meta.QTalkKeyword;
import com.tht.api.app.entity.meta.QTalkKeywordImg;
import com.tht.api.app.repository.mapper.DailyFallingMapper;
import com.tht.api.app.repository.mapper.QDailyFallingMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DailyFallingCustomRepositoryImpl implements DailyFallingCustomRepository {

    private static final QDailyFalling dailyFalling = QDailyFalling.dailyFalling;
    private static final QTalkKeyword talkKeyword = QTalkKeyword.talkKeyword;
    private static final QTalkKeywordImg talkKeywordImg = QTalkKeywordImg.talkKeywordImg;
    private static final QDailyFallingActiveTimeTable dailyFallingActiveTimeTable = QDailyFallingActiveTimeTable.dailyFallingActiveTimeTable;

    private final JPAQueryFactory queryFactory;

    @Override
    public List<DailyFallingMapper> findAllDailyFallingInfo(final Integer activeTableIdx) {

        return queryFactory.select(
                new QDailyFallingMapper(
                    dailyFalling.idx,
                    talkKeyword.keyword,
                    talkKeywordImg.imgUrl,
                    talkKeyword.talkIssue
                ))
            .from(dailyFalling)
            .innerJoin(talkKeyword)
            .on(dailyFalling.talkKeywordIdx.eq(talkKeyword.idx))
            .innerJoin(talkKeywordImg)
            .on(talkKeyword.talkKeywordImgIdx.eq(talkKeywordImg.idx))
            .innerJoin(dailyFallingActiveTimeTable)
            .on(dailyFalling.activeTimeTableIdx.eq(dailyFallingActiveTimeTable.idx))
            .where(dailyFallingActiveTimeTable.idx.eq(activeTableIdx))
            .fetch();
    }

}
