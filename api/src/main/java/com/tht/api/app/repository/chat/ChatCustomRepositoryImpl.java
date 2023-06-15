package com.tht.api.app.repository.chat;

import com.tht.api.app.entity.ChatHistory;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

@RequiredArgsConstructor
public class ChatCustomRepositoryImpl implements ChatCustomRepository{

    private final MongoTemplate mongoTemplate;

    @Override
    public List<ChatHistory> findAllCursorPagingBy(final long chatRoomId, final String chatIdx,
        final int size) {

        Query query = new Query();

        query.addCriteria(Criteria.where("room_idx").is(chatRoomId));

        if(Objects.nonNull(chatIdx)) {
            query.addCriteria(Criteria.where("_id").lt(chatIdx));
        }

        query.with(Sort.by(Direction.DESC, "_id")).limit(size);

        return mongoTemplate.find(query, ChatHistory.class);

    }
}
