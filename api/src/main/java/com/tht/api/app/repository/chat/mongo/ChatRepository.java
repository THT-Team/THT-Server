package com.tht.api.app.repository.chat.mongo;

import com.tht.api.app.entity.chat.ChatHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ChatRepository extends MongoRepository<ChatHistory, Long>, ChatCustomRepository {

}
