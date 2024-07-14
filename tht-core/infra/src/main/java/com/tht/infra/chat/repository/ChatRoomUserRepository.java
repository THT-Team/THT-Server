package com.tht.infra.chat.repository;


import com.tht.infra.chat.ChatRoomUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomUserRepository extends JpaRepository<ChatRoomUser, Long>,
    ChatRoomUserCustomRepository {
}
