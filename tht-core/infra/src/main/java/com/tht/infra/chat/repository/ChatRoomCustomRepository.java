package com.tht.infra.chat.repository;



import com.tht.infra.chat.mapper.ChatRoomMapper;

import java.util.List;

public interface ChatRoomCustomRepository {

    List<ChatRoomMapper> findMyChatRoomInfos(final Long chatRoomIdx, final String userUuid);
}
