package com.tht.thtapis.facade.chat.response;

import com.tht.infra.chat.ChatHistory;
import com.tht.infra.chat.mapper.ChatRoomPreviewMapper;

import java.time.LocalDateTime;
import java.util.Objects;

public record ChatRoomPreviewResponse(
    long chatRoomIdx,
    String partnerName,
    String partnerProfileUrl,
    String currentMessage,
    LocalDateTime messageTime,
    boolean isAvailableChat
) {

    private static final String START_PREVIEW_MESSAGE = "매칭된 무디와 먼저 대화를 시작해 보세요.";
    private static final String DISABLED_PREVIEW_MESSAGE = "현재 메세지를 보낼 수 없습니다.";

    public static ChatRoomPreviewResponse of(final ChatRoomPreviewMapper mapper,
        final ChatHistory chatHistory) {

        return new ChatRoomPreviewResponse(
            mapper.chatRoomIdx(),
            mapper.partnerName(),
            mapper.partnerProfileUrl(),
            converterStartMsg(chatHistory.getMsg()),
            chatHistory.getCreatedAt(),
            true
        );
    }

    private static String converterStartMsg(final String msg) {
        if (Objects.isNull(msg)) {
            return START_PREVIEW_MESSAGE;
        }
        return msg;
    }

    public static ChatRoomPreviewResponse startChatRoom(final ChatRoomPreviewMapper mapper) {

        return new ChatRoomPreviewResponse(
            mapper.chatRoomIdx(),
            mapper.partnerName(),
            mapper.partnerProfileUrl(),
            START_PREVIEW_MESSAGE,
            mapper.chatRoomCreatedAt(),
            true
        );
    }

    public static ChatRoomPreviewResponse unavailableChatRoom(final ChatRoomPreviewMapper mapper) {

        return new ChatRoomPreviewResponse(
            mapper.chatRoomIdx(),
            mapper.partnerName(),
            mapper.partnerProfileUrl(),
            DISABLED_PREVIEW_MESSAGE,
            mapper.chatRoomCreatedAt(),
            false
        );

    }
}
