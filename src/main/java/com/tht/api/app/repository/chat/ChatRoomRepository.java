package com.tht.api.app.repository.chat;

import com.tht.api.app.entity.chat.ChatRoom;
import com.tht.api.app.repository.chat.querydsl.ChatRoomCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long>,
    ChatRoomCustomRepository {

}
