package com.tht.api.app.facade.chat.group;

import com.tht.api.app.entity.chat.ChatHistory;
import java.util.List;
import java.util.Optional;

public record ChatHistoryGroup(
    List<ChatHistory> list
) {

    public static ChatHistoryGroup of(final List<ChatHistory> allCurrentMessageIn) {
        return new ChatHistoryGroup(allCurrentMessageIn);
    }

    public Optional<ChatHistory> findByRoomIdx(final long roomIdx) {
        return list.stream()
            .filter(chatHistory -> chatHistory.getRoomIdx().equals(roomIdx))
            .findFirst();
    }
}
