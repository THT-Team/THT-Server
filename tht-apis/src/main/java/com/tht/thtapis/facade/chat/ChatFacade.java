package com.tht.thtapis.facade.chat;

import com.tht.domain.entity.chat.group.ChatRoomMapperGroup;
import com.tht.domain.entity.chat.mapper.ChatRoomUserMapper;
import com.tht.domain.entity.user.User;
import com.tht.thtapis.facade.Facade;
import com.tht.thtapis.facade.chat.group.ChatHistoryGroup;
import com.tht.thtapis.facade.chat.group.ChatRoomPreviewMapperGroup;
import com.tht.thtapis.facade.chat.group.ChatRoomPreviewResponseGroup;
import com.tht.thtapis.facade.chat.response.ChatResponse;
import com.tht.thtapis.facade.chat.response.ChatRoomPreviewResponse;
import com.tht.thtapis.facade.chat.response.ChatRoomResponse;
import java.util.List;

import com.tht.domain.entity.chat.service.ChatRoomService;
import com.tht.domain.entity.chat.service.ChatRoomUserService;
import com.tht.domain.entity.chat.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Facade
@RequiredArgsConstructor
public class ChatFacade {

    private final ChatService chatService;
    private final ChatRoomService chatRoomService;
    private final ChatRoomUserService chatRoomUserService;

    @Transactional
    public List<ChatResponse> readHistory(final long chatRoomId, final String chatIdx,
        final int size) {

        return chatService.readHistory(chatRoomId, chatIdx, size)
            .stream()
            .map(ChatResponse::of)
            .toList();
    }

    public List<ChatRoomPreviewResponse> findMyRoomList(final String userUuid) {

        final ChatRoomPreviewMapperGroup mapperGroup = ChatRoomPreviewMapperGroup.of(
            chatRoomUserService.findMyChatRoomPreviewInfo(userUuid));

        final ChatHistoryGroup chatHistoryGroup = ChatHistoryGroup.of(
            chatService.findAllCurrentMessageIn(mapperGroup.getChatRoomIdx()));

        return ChatRoomPreviewResponseGroup
            .of(mapperGroup.mapperList(), chatHistoryGroup)
            .responses();

    }

    @Transactional
    public void outChatting(final long chatRoomIdx, final String userUuid) {

        chatRoomUserService.outChatRoom(chatRoomIdx, userUuid);
    }

    public ChatRoomResponse findMyRoomDetail(final long chatRoomIdx, final String userUuid) {

        chatRoomService.existBy(chatRoomIdx);

        final ChatRoomMapperGroup group = chatRoomService.findDetailInfoById(chatRoomIdx, userUuid);
        final List<ChatRoomUserMapper> allParticipator = chatRoomUserService.findAllParticipator(chatRoomIdx);

        return ChatRoomResponse.of(group.getBasic(), group.isChatAble(), allParticipator);
    }
}
