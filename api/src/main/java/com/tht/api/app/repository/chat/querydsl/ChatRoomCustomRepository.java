package com.tht.api.app.repository.chat.querydsl;

import com.tht.api.app.repository.mapper.ChatRoomMapper;

public interface ChatRoomCustomRepository {

    ChatRoomMapper findRoomFallingInfoBy(final Long chatRoomIdx);
}
