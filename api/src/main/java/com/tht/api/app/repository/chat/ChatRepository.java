package com.tht.api.app.repository.chat;

import com.tht.api.app.entity.ChatHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends MongoRepository<ChatHistory, Long>, ChatCustomRepository {

}
