package com.tht.domain.entity.chat.service;

import com.tht.domain.entity.chat.ChatHistory;
import com.tht.domain.entity.chat.mapper.ChatHistoryMapper;
import com.tht.domain.entity.chat.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;

    public List<ChatHistory> readHistory(final long chatRoomId, final String chatIdx,
                                         final int size) {

        return chatRepository.findAllCursorPagingBy(chatRoomId, chatIdx, size);
    }

    public List<ChatHistory> findAllCurrentMessageIn(final List<Long> chatRoomIdxList) {

        return chatRepository.findAllCurrentMsgIn(chatRoomIdxList).stream()
            .map(ChatHistoryMapper::chatHistory).toList();
    }
}
