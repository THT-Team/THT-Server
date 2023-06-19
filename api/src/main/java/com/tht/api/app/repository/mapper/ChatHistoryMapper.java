package com.tht.api.app.repository.mapper;

import com.tht.api.app.entity.chat.ChatHistory;

public record ChatHistoryMapper(
    Long _id,
    ChatHistory chatHistory
) {

}
