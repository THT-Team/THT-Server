package com.tht.api.app.service;

import com.tht.api.app.entity.chat.ChatRoomUser;
import com.tht.api.app.repository.chat.ChatRoomUserRepository;
import com.tht.api.app.repository.mapper.ChatRoomPreviewMapper;
import java.util.List;
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
}
