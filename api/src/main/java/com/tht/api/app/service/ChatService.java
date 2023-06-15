package com.tht.api.app.service;

import com.tht.api.app.entity.ChatHistory;
import com.tht.api.app.repository.chat.ChatRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;

    public List<ChatHistory> readHistory(final long chatRoomId, final String chatIdx,
        final int size) {

        return chatRepository.findAllCursorPagingBy(chatRoomId, chatIdx, size);
    }
}
