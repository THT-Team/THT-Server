package com.tht.api.app.service;

import com.tht.api.app.entity.chat.ChatRoom;
import com.tht.api.app.repository.chat.ChatRoomRepository;
import com.tht.api.app.repository.mapper.ChatRoomMapper;
import com.tht.api.exception.custom.EntityStateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public ChatRoomMapper findDetailInfoById(final Long chatRoomIdx) {
        return chatRoomRepository.findRoomFallingInfoBy(chatRoomIdx);
    }

    public void existBy(final long chatRoomIdx) {
        if (!chatRoomRepository.existsById(chatRoomIdx)) {
            throw EntityStateException.doNotExistOf(ChatRoom.class.getSimpleName());
        }
    }

    public long makeRoomAndGetIdx(final long dailyTopicIdx) {
        return save(dailyTopicIdx).getIdx();
    }

    private ChatRoom save(long dailyTopicIdx) {
        return chatRoomRepository.save(ChatRoom.create(dailyTopicIdx));
    }
}
