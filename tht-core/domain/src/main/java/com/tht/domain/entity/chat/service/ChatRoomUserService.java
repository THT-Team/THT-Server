package com.tht.domain.entity.chat.service;

import java.util.List;

import com.tht.domain.entity.chat.ChatRoomUser;
import com.tht.domain.entity.chat.mapper.ChatRoomPreviewMapper;
import com.tht.domain.entity.chat.repository.ChatRoomUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatRoomUserService {

    private final ChatRoomUserRepository chatRoomUserRepository;

    public List<ChatRoomPreviewMapper> findMyChatRoomPreviewInfo(final String userUuid) {
        return chatRoomUserRepository.findAllByUserUuidInActive(userUuid);
    }

    public void outChatRoom(final long chatRoomIdx, final String userUuid) {
        chatRoomUserRepository.updateChatRoomUserInActive(chatRoomIdx, userUuid);
    }

    public void inChatRoom(final long chatRoomIdx, final String userUuid) {
        chatRoomUserRepository.save(ChatRoomUser.create(chatRoomIdx, userUuid));
    }

    public void outOfBlockChatRoom(final String userUuid, final String blockUserUuid) {
        chatRoomUserRepository.updateChatRoomUserInActiveOfBlock(userUuid, blockUserUuid);
    }
}
