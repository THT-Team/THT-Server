package com.tht.api.app.repository.group;

import com.tht.api.app.repository.mapper.ChatRoomMapper;
import java.util.List;
import java.util.function.Predicate;

public record ChatRoomMapperGroup(List<ChatRoomMapper> mappers) {

    private static final int FIRST = 0;

    public static ChatRoomMapperGroup of(List<ChatRoomMapper> chatRoomInfos) {
        return new ChatRoomMapperGroup(chatRoomInfos);
    }

    public boolean isChatAble() {
        return mappers.stream().anyMatch(checkChatAble());
    }

    private static Predicate<ChatRoomMapper> checkChatAble() {
        return mapper -> mapper.isActiveUser() && mapper.isNonOutRoomUser() ;
    }

    public ChatRoomMapper getBasic() {
        return mappers.get(FIRST);
    }
}
