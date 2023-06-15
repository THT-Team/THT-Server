package com.tht.api.app.facade.chat;

import com.tht.api.app.facade.Facade;
import com.tht.api.app.facade.chat.response.ChatResponse;
import com.tht.api.app.service.ChatService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Facade
@Transactional
@RequiredArgsConstructor
public class ChatFacade {

    private final ChatService chatService;

    public List<ChatResponse> readHistory(final long chatRoomId, final String chatIdx,
        final int size) {

        return chatService.readHistory(chatRoomId, chatIdx, size)
            .stream()
            .map(ChatResponse::of)
            .toList();
    }

}
