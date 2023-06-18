package com.tht.api.app.repository.chat.mongo;

import com.tht.api.app.entity.chat.ChatHistory;
import java.util.List;

public interface ChatCustomRepository {

    List<ChatHistory> findAllCursorPagingBy(final long chatRoomId, final String chatIdx,
        final int size);

    List<ChatHistory> findAllCurrentMsgIn(final List<Long> chatRoomIdxList);
}
