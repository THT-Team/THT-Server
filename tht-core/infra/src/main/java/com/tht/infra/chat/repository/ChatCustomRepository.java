package com.tht.infra.chat.repository;


import com.tht.infra.chat.ChatHistory;
import com.tht.infra.chat.mapper.ChatHistoryMapper;

import java.util.List;

public interface ChatCustomRepository {

    List<ChatHistory> findAllCursorPagingBy(final long chatRoomId, final String chatIdx,
                                            final int size);

    List<ChatHistoryMapper> findAllCurrentMsgIn(final List<Long> chatRoomIdxList);
}
