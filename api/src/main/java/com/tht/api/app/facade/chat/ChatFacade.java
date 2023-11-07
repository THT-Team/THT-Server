package com.tht.api.app.facade.chat;

import com.tht.api.app.facade.Facade;
import com.tht.api.app.facade.chat.group.ChatHistoryGroup;
import com.tht.api.app.facade.chat.group.ChatRoomPreviewMapperGroup;
import com.tht.api.app.facade.chat.group.ChatRoomPreviewResponseGroup;
import com.tht.api.app.facade.chat.response.ChatResponse;
import com.tht.api.app.facade.chat.response.ChatRoomPreviewResponse;
import com.tht.api.app.facade.chat.response.ChatRoomResponse;
import com.tht.api.app.service.ChatRoomService;
import com.tht.api.app.service.ChatRoomUserService;
import com.tht.api.app.service.ChatService;
import java.util.List;
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

    public ChatRoomResponse findMyRoomDetail(final long chatRoomIdx) {

        chatRoomService.existBy(chatRoomIdx);

        return ChatRoomResponse.of(chatRoomService.findDetailInfoById(chatRoomIdx));
    }
}
