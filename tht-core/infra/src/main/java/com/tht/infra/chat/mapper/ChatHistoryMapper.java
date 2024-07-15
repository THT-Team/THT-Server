package com.tht.infra.chat.mapper;

import com.tht.infra.chat.ChatHistory;

public record ChatHistoryMapper(
    Long _id,
    ChatHistory chatHistory
) {

}
