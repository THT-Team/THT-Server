package com.tht.domain.entity.chat.repository;


import com.tht.domain.entity.chat.ChatHistory;
import com.tht.domain.entity.chat.mapper.ChatHistoryMapper;

import java.util.List;

public interface ChatCustomRepository {

    List<ChatHistory> findAllCursorPagingBy(final long chatRoomId, final String chatIdx,
                                            final int size);

    List<ChatHistoryMapper> findAllCurrentMsgIn(final List<Long> chatRoomIdxList);
}
