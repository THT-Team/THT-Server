package com.tht.domain.entity.chat.service;

import com.tht.domain.entity.chat.ChatRoom;
import com.tht.domain.entity.chat.group.ChatRoomMapperGroup;
import com.tht.domain.entity.chat.repository.ChatRoomRepository;
import com.tht.domain.exception.EntityStateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public ChatRoomMapperGroup findDetailInfoById(final Long chatRoomIdx, final String userUuid) {

        return ChatRoomMapperGroup.of(chatRoomRepository.findMyChatRoomInfos(chatRoomIdx, userUuid));
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
