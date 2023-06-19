package com.tht.api.app.facade.chat;

import com.tht.api.app.entity.chat.ChatHistory;
import com.tht.api.app.facade.Facade;
import com.tht.api.app.facade.chat.group.ChatRoomPreviewMapperGroup;
import com.tht.api.app.facade.chat.response.ChatResponse;
import com.tht.api.app.facade.chat.response.ChatRoomPreviewResponse;
import com.tht.api.app.repository.mapper.ChatRoomPreviewMapper;
import com.tht.api.app.service.ChatRoomUserService;
import com.tht.api.app.service.ChatService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Facade
@RequiredArgsConstructor
public class ChatFacade {

    private final ChatService chatService;
    private final ChatRoomUserService chatRoomUserService;

    @Transactional
    public List<ChatResponse> readHistory(final long chatRoomId, final String chatIdx,
        final int size) {

        return chatService.readHistory(chatRoomId, chatIdx, size)
            .stream()
            .map(ChatResponse::of)
            .toList();
    }

    public List<ChatRoomPreviewResponse> findMyRoom(final String userUuid) {

        final ChatRoomPreviewMapperGroup mapperGroup = ChatRoomPreviewMapperGroup.of(
            chatRoomUserService.findMyChatRoomPreviewInfo(userUuid));

        final List<ChatHistory> chatHistories = chatService.findAllCurrentMessageIn(
            mapperGroup.getChatRoomIdx());

        final List<ChatRoomPreviewResponse> responseList = new ArrayList<>();

        int index = 0;

        for (ChatRoomPreviewMapper mapper : mapperGroup.mapperList()) {

            final ChatHistory chatHistory = chatHistories.get(index);
            index = getIndex(responseList, index, mapper, chatHistory);
        }

        return responseList;
    }

    private static int getIndex(final List<ChatRoomPreviewResponse> responseList, int index,
        final ChatRoomPreviewMapper mapper, final ChatHistory chatHistory) {

        if (mapper.chatRoomIdx().equals(chatHistory.getRoomIdx())) {
            responseList.add(ChatRoomPreviewResponse.of(mapper, chatHistory));
            return ++index;
        }
        responseList.add(ChatRoomPreviewResponse.of(mapper));

        return index;
    }

}
