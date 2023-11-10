package com.tht.api.app.repository.chat.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tht.api.app.entity.chat.QChatRoom;
import com.tht.api.app.entity.chat.QChatRoomUser;
import com.tht.api.app.entity.meta.QDailyFalling;
import com.tht.api.app.entity.meta.QTalkKeyword;
import com.tht.api.app.entity.user.QUser;
import com.tht.api.app.repository.mapper.ChatRoomMapper;
import com.tht.api.app.repository.mapper.QChatRoomMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChatRoomCustomRepositoryImpl implements ChatRoomCustomRepository{

    private static final QChatRoom chatRoom = QChatRoom.chatRoom;
    private static final QDailyFalling dailyFalling = QDailyFalling.dailyFalling;
    private static final QTalkKeyword talkKeyword = QTalkKeyword.talkKeyword;
    private static final QChatRoomUser chatRoomUser = QChatRoomUser.chatRoomUser;
    private static final QUser user = QUser.user;

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ChatRoomMapper> findMyChatRoomInfos(final Long chatRoomIdx, final String userUuid) {

        return jpaQueryFactory.select(
                new QChatRoomMapper(
                    chatRoom.idx,
                    talkKeyword.keyword,
                    dailyFalling.talkIssue,
                    chatRoom.createdAt,
                    chatRoomUser.state,
                    user.state
                )
            )
            .from(chatRoom)
            .innerJoin(dailyFalling)
            .on(chatRoom.dailyFallingIdx.eq(dailyFalling.idx))
            .innerJoin(talkKeyword)
            .on(dailyFalling.talkKeywordIdx.eq(talkKeyword.idx))
            .innerJoin(chatRoomUser)
            .on(chatRoom.idx.eq(chatRoomUser.chatRoomIdx))
            .innerJoin(user)
            .on(chatRoomUser.userUuid.eq(user.userUuid))
            .where(
                chatRoom.idx.eq(chatRoomIdx),
                user.userUuid.ne(userUuid)
            )
            .fetch();
    }

}
