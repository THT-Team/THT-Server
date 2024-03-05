package com.tht.api.app.facade.chat.group;

import com.tht.api.app.entity.chat.ChatHistory;
import com.tht.api.app.facade.chat.response.ChatRoomPreviewResponse;
import com.tht.api.app.repository.mapper.ChatRoomPreviewMapper;
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
