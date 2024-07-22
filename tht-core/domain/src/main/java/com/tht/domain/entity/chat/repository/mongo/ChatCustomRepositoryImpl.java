package com.tht.domain.entity.chat.repository.mongo;

import com.mongodb.BasicDBObject;
import com.tht.domain.entity.chat.ChatHistory;
import com.tht.domain.entity.chat.mapper.ChatHistoryMapper;
import com.tht.domain.entity.chat.repository.ChatCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;
import java.util.Objects;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@RequiredArgsConstructor
public class ChatCustomRepositoryImpl implements ChatCustomRepository {

    private final MongoTemplate mongoTemplate;

    @Override
    public List<ChatHistory> findAllCursorPagingBy(final long chatRoomId, final String chatIdx,
                                                   final int size) {

        Query query = new Query();

        query.addCriteria(where("room_idx").is(chatRoomId));

        if(Objects.nonNull(chatIdx)) {
            query.addCriteria(where("_id").lt(chatIdx));
        }

        query.with(Sort.by(Direction.DESC, "_id")).limit(size);

        return mongoTemplate.find(query, ChatHistory.class);

    }

    @Override
    public List<ChatHistoryMapper> findAllCurrentMsgIn(final List<Long> chatRoomIdxList) {

        MatchOperation matchOperation = Aggregation.match(
            where("room_idx").in(chatRoomIdxList)
        );

        SortOperation sortOperation = Aggregation.sort(Direction.DESC, "created_at");

        GroupOperation groupOperation = Aggregation.group("$room_idx")
            .push(
                new BasicDBObject("_id", "$_id")
                    .append("room_idx", "$room_idx")
                    .append("msg", "$msg")
                    .append("sender_name", "$sender_name")
                    .append("sender_uuid", "$sender_uuid")
                    .append("created_at", "$created_at")
            ).as("chatHistory");

        ProjectionOperation projectOperation = Aggregation.project().and(
            ArrayOperators.ArrayElemAt.arrayOf("chatHistory").elementAt(0)).as("chatHistory");

        Aggregation aggregation = Aggregation.newAggregation(
            matchOperation, sortOperation, groupOperation, projectOperation
        );

        return mongoTemplate.aggregate(aggregation, "chat_history", ChatHistoryMapper.class)
            .getMappedResults();
    }

}
