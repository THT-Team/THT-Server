package com.tht.api.app.repository.chat;

import com.tht.api.app.entity.chat.ChatRoomUser;
import com.tht.api.app.repository.chat.querydsl.ChatRoomUserCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomUserRepository extends JpaRepository<ChatRoomUser, Long>,
    ChatRoomUserCustomRepository {
}
