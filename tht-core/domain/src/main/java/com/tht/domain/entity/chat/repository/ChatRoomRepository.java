package com.tht.domain.entity.chat.repository;


import com.tht.domain.entity.chat.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long>,
    ChatRoomCustomRepository {

}
