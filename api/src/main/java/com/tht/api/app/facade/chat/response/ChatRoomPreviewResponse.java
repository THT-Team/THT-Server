package com.tht.api.app.facade.chat.response;

import com.tht.api.app.entity.chat.ChatHistory;
import com.tht.api.app.repository.mapper.ChatRoomPreviewMapper;
import java.time.LocalDateTime;
import java.util.Objects;

public record ChatRoomPreviewResponse(
    long chatRoomIdx,
    String partnerName,
    String partnerProfileUrl,
    String currentMessage,
    LocalDateTime messageTime
) {

    private static final String START_PREVIEW_MESSAGE = "매칭된 무디와 먼저 대화를 시작해 보세요";

    public static ChatRoomPreviewResponse of(final ChatRoomPreviewMapper mapper,
        final ChatHistory chatHistory) {

        return new ChatRoomPreviewResponse(
            mapper.chatRoomIdx(),
            mapper.partnerName(),
            mapper.partnerProfileUrl(),
            converterStartMsg(chatHistory.getMsg()),
            chatHistory.getCreatedAt()
        );
    }

    private static String converterStartMsg(final String msg) {
        if (Objects.isNull(msg)) {
            return START_PREVIEW_MESSAGE;
        }
        return msg;
    }

    public static ChatRoomPreviewResponse of(final ChatRoomPreviewMapper mapper) {
        return new ChatRoomPreviewResponse(
            mapper.chatRoomIdx(),
            mapper.partnerName(),
            mapper.partnerProfileUrl(),
            START_PREVIEW_MESSAGE,
            mapper.chatRoomCreatedAt()
        );
    }
}
