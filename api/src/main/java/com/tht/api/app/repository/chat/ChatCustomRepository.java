package com.tht.api.app.repository.chat;

import com.tht.api.app.entity.ChatHistory;
import java.util.List;

public interface ChatCustomRepository {

    List<ChatHistory> findAllCursorPagingBy(final long chatRoomId, final String chatIdx,
        final int size);
}
