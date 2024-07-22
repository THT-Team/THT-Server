package com.tht.domain.entity.chat.repository;


import com.tht.domain.entity.chat.ChatRoomUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomUserRepository extends JpaRepository<ChatRoomUser, Long>,
    ChatRoomUserCustomRepository {
}
