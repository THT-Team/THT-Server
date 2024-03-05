package com.tht.api.app.repository.chat.querydsl;

import com.tht.api.app.repository.mapper.ChatRoomMapper;
import java.util.List;

public interface ChatRoomCustomRepository {

    List<ChatRoomMapper> findMyChatRoomInfos(final Long chatRoomIdx, final String userUuid);
}
