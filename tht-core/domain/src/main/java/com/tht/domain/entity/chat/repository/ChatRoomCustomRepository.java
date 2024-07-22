package com.tht.domain.entity.chat.repository;



import com.tht.domain.entity.chat.mapper.ChatRoomMapper;

import java.util.List;

public interface ChatRoomCustomRepository {

    List<ChatRoomMapper> findMyChatRoomInfos(final Long chatRoomIdx, final String userUuid);
}
