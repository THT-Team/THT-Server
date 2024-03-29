package com.tht.api.app.facade.chat.group;

import com.tht.api.app.repository.mapper.ChatRoomPreviewMapper;
import java.util.List;

public record ChatRoomPreviewMapperGroup(
    List<ChatRoomPreviewMapper> mapperList
) {

    public static ChatRoomPreviewMapperGroup of(final List<ChatRoomPreviewMapper> list) {
        return new ChatRoomPreviewMapperGroup(list);
    }


    public List<Long> getChatRoomIdx() {

        return mapperList.stream().map(ChatRoomPreviewMapper::chatRoomIdx).toList();
    }
}
