package com.tht.thtapis.facade.chat.response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.mock;

import com.tht.infra.chat.ChatHistory;
import com.tht.thtapis.fixture.chat.ChatRoomPreviewMapperFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChatRoomPreviewResponseTest {

    @Test
    @DisplayName("채팅방 리스트 조회 시 대화가 시작되지않았을 때 - default 메시지 반환")
    void convertDefaultMsg() {

        ChatHistory mock = mock(ChatHistory.class);
        ChatRoomPreviewResponse response = ChatRoomPreviewResponse.of(
            ChatRoomPreviewMapperFixture.make(1L), mock);

        assertThat(response.currentMessage()).isEqualTo("매칭된 무디와 먼저 대화를 시작해 보세요.");
    }
}