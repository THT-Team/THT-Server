package com.tht.thtapis.facade.chat.response;

import com.tht.domain.entity.chat.ChatHistory;
import java.time.Instant;

public record ChatResponse(
    String chatIdx,
    String sender,
    String senderUuid,
    String msg,
    String imgUrl,
    Instant dateTime
) {

    public static ChatResponse of(final ChatHistory entity) {
        return new ChatResponse(
            entity.getId(),
            entity.getSenderName(),
            entity.getSenderUuid(),
            entity.getMsg(),
            entity.getImgUrl(),
            entity.getCreatedAt()
        );
    }
}
