package com.tht.domain.entity.chat.repository;


import com.tht.domain.entity.chat.mapper.ChatRoomPreviewMapper;

import java.util.List;

public interface ChatRoomUserCustomRepository {

    List<ChatRoomPreviewMapper> findAllByUserUuidInActive(final String userUuid);

    void updateChatRoomUserInActive(final long chatRoomIdx, final String userUuid);

    void updateChatRoomUserInActiveOfBlock(final String userUuid, final String blockUserUuid);
}
