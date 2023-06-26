package com.tht.api.app.repository.chat.querydsl;

import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tht.api.app.entity.chat.QChatRoom;
import com.tht.api.app.entity.meta.QDailyFalling;
import com.tht.api.app.entity.meta.QTalkKeyword;
import com.tht.api.app.repository.mapper.ChatRoomMapper;
import com.tht.api.app.repository.mapper.QChatRoomMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChatRoomCustomRepositoryImpl implements ChatRoomCustomRepository{

    private static final QChatRoom chatRoom = QChatRoom.chatRoom;
    private static final QDailyFalling dailyFalling = QDailyFalling.dailyFalling;
    private static final QTalkKeyword talkKeyword = QTalkKeyword.talkKeyword;

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public ChatRoomMapper findRoomFallingInfoBy(final Long chatRoomIdx) {

        return jpaQueryFactory.select(
                new QChatRoomMapper(
                    chatRoom.idx,
                    talkKeyword.keyword,
                    talkKeyword.talkIssue,
                    Expressions.stringTemplate("DATE_FORMAT({0}, {1})", chatRoom.createdAt,
                        ConstantImpl.create("%Y년 %m월 %d일"))
                )
            )
            .from(chatRoom)
            .innerJoin(dailyFalling)
            .on(chatRoom.dailyFallingIdx.eq(dailyFalling.idx))
            .innerJoin(talkKeyword)
            .on(dailyFalling.talkKeywordIdx.eq(talkKeyword.idx))
            .where(chatRoom.idx.eq(chatRoomIdx))
            .fetchFirst();
    }

}
