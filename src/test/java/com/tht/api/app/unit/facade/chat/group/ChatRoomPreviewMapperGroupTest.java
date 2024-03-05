package com.tht.api.app.unit.facade.chat.group;

import static org.assertj.core.api.Assertions.assertThat;

import com.tht.api.app.facade.chat.group.ChatRoomPreviewMapperGroup;
import com.tht.api.app.unit.fixture.chat.ChatRoomPreviewMapperFixture;
import com.tht.api.app.repository.mapper.ChatRoomPreviewMapper;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChatRoomPreviewMapperGroupTest {

    @Test
    @DisplayName("chatRoomPreviewGroup 채팅방 리스트 추출 테스트")
    void getChatRoomIdxList() {

        //given
        ChatRoomPreviewMapper mock1 = ChatRoomPreviewMapperFixture.make(1L);
        ChatRoomPreviewMapper mock2 = ChatRoomPreviewMapperFixture.make(3L);
        ChatRoomPreviewMapper mock3 = ChatRoomPreviewMapperFixture.make(5L);

        //when
        ChatRoomPreviewMapperGroup group = ChatRoomPreviewMapperGroup.of(
            List.of(mock1, mock2, mock3));

        //then
        assertThat(group.getChatRoomIdx()).contains(1L,3L,5L);
    }
}