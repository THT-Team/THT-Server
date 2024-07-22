package com.tht.domain.entity.chat.repository;

import com.tht.domain.entity.chat.ChatHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRepository extends MongoRepository<ChatHistory, Long>, ChatCustomRepository {

}
