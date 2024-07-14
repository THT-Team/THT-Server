package com.tht.infra.chat.repository;

import com.tht.infra.chat.ChatHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRepository extends MongoRepository<ChatHistory, Long>, ChatCustomRepository {

}
