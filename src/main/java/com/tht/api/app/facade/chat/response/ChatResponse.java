package com.tht.api.app.facade.chat.response;

import com.tht.api.app.entity.chat.ChatHistory;
import java.time.LocalDateTime;

public record ChatResponse(
    String chatIdx,
    String sender,
    String senderUuid,
    String msg,
    String imgUrl,
    LocalDateTime dateTime
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
