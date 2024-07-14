package com.tht.infra.chat.repository;


import com.tht.infra.chat.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long>,
    ChatRoomCustomRepository {

}
