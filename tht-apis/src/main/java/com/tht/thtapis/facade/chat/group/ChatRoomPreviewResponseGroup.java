package com.tht.thtapis.facade.chat.group;

import com.tht.domain.entity.chat.ChatHistory;
import com.tht.domain.entity.chat.mapper.ChatRoomPreviewMapper;
import com.tht.thtapis.facade.chat.response.ChatRoomPreviewResponse;

import java.util.List;
import java.util.Optional;

public record ChatRoomPreviewResponseGroup(
    List<ChatRoomPreviewResponse> responses
) {


    public static ChatRoomPreviewResponseGroup of(final List<ChatRoomPreviewMapper> mapperList,
        final ChatHistoryGroup chatHistoryGroup) {

        return new ChatRoomPreviewResponseGroup(
            mapperList.parallelStream()
                .map(mapper -> factory(mapper, chatHistoryGroup))
                .toList()
        );
    }

    private static ChatRoomPreviewResponse factory(ChatRoomPreviewMapper mapper,
        ChatHistoryGroup chatHistoryGroup) {

        if (mapper.isChatOutPartner()) {
            return ChatRoomPreviewResponse.unavailableChatRoom(mapper);
        }

        final Optional<ChatHistory> chatHistory = chatHistoryGroup.findByRoomIdx(mapper.chatRoomIdx());

        return chatHistory
            .map(history -> ChatRoomPreviewResponse.of(mapper, history))
            .orElseGet(() -> ChatRoomPreviewResponse.startChatRoom(mapper));

    }
}
