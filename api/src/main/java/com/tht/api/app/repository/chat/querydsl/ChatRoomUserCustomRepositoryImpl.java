package com.tht.api.app.repository.chat.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.tht.api.app.entity.chat.QChatRoom;
import com.tht.api.app.entity.chat.QChatRoomUser;
import com.tht.api.app.entity.enums.EntityState;
import com.tht.api.app.entity.user.QUser;
import com.tht.api.app.entity.user.QUserProfilePhoto;
import com.tht.api.app.repository.mapper.ChatRoomPreviewMapper;
import com.tht.api.app.repository.mapper.QChatRoomPreviewMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChatRoomUserCustomRepositoryImpl implements ChatRoomUserCustomRepository {


    private static final QChatRoom chatRoom = QChatRoom.chatRoom;
    private static final QChatRoomUser chatRoomUser = QChatRoomUser.chatRoomUser;
    private static final QUser user = QUser.user;
    private static final QUserProfilePhoto userProfilePhoto = QUserProfilePhoto.userProfilePhoto;

    private static final int MAIN_PROFILE_IMG = 1;

    private final JPAQueryFactory queryFactory;

    @Override
    public List<ChatRoomPreviewMapper> findAllByUserUuidInActive(final String userUuid) {

        final QChatRoomUser chatRoomPartnerUser = new QChatRoomUser("partnerChatRoomUser");

        return queryFactory.select(
                new QChatRoomPreviewMapper(
                    chatRoomUser.chatRoomIdx,
                    chatRoomPartnerUser.userUuid,
                    user.username,
                    userProfilePhoto.url,
                    chatRoom.createdAt,
                    chatRoomPartnerUser.state
                ))
            .from(chatRoomUser)
            .innerJoin(chatRoomPartnerUser)
            .on(chatRoomPartnerUser.chatRoomIdx.eq(chatRoomUser.chatRoomIdx)
                .and(chatRoomPartnerUser.userUuid.ne(userUuid)))
            .innerJoin(chatRoom)
            .on(chatRoom.idx.eq(chatRoomUser.chatRoomIdx))
            .innerJoin(user)
            .on(user.userUuid.eq(chatRoomPartnerUser.userUuid))
            .innerJoin(userProfilePhoto)
            .on(userProfilePhoto.userUuid.eq(chatRoomPartnerUser.userUuid)
                .and(userProfilePhoto.priority.eq(MAIN_PROFILE_IMG)))
            .where(chatRoomUser.userUuid.eq(userUuid)
                .and(chatRoomUser.state.eq(EntityState.ACTIVE))
            )
            .fetch();
    }

    @Override
    public void updateChatRoomUserInActive(final long chatRoomIdx, final String userUuid) {

        queryFactory.update(chatRoomUser)
            .set(chatRoomUser.state, EntityState.INACTIVE)
            .where(chatRoomUser.chatRoomIdx.eq(chatRoomIdx)
                .and(chatRoomUser.userUuid.eq(userUuid)))
            .execute();
    }

    @Override
    public void updateChatRoomUserInActiveOfBlock(final String userUuid,
        final String blockUserUuid) {

        final List<Long> blockUserChatRoomIdxList = queryFactory.select(chatRoomUser.chatRoomIdx)
            .from(chatRoomUser)
            .where(
                chatRoomUser.userUuid.eq(blockUserUuid),
                chatRoomUser.state.eq(EntityState.ACTIVE)
            )
            .fetch();

        queryFactory.update(chatRoomUser)
            .set(chatRoomUser.state, EntityState.INACTIVE)
            .where(
                chatRoomUser.chatRoomIdx.in(blockUserChatRoomIdxList),
                chatRoomUser.userUuid.eq(userUuid))
            .execute();
    }

}
