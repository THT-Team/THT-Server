package com.tht.api.app.repository.chat.mongo;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import com.mongodb.BasicDBObject;
import com.tht.api.app.entity.chat.ChatHistory;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.ArrayOperators;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Query;

@RequiredArgsConstructor
public class ChatCustomRepositoryImpl implements ChatCustomRepository{

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
    public List<ChatHistory> findAllCurrentMsgIn(final List<Long> chatRoomIdxList) {

        //todo. null 값이 들어가면 메세지 넣어주자

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
            ).as("info");

        ProjectionOperation projectOperation = Aggregation.project().and(
            ArrayOperators.ArrayElemAt.arrayOf("info").elementAt(0)).as("info");

        Aggregation aggregation = Aggregation.newAggregation(
            matchOperation, sortOperation, groupOperation, projectOperation
        );

        return mongoTemplate.aggregate(aggregation, "chat_history", ChatHistory.class)
            .getMappedResults();
    }

}
