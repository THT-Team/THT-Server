package com.tht.api.app.facade.chat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.anyInt;
import static org.mockito.BDDMockito.anyLong;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.when;
import static org.mockito.Mockito.verify;

import com.tht.api.app.entity.chat.ChatHistory;
import com.tht.api.app.facade.chat.response.ChatResponse;
import com.tht.api.app.service.ChatRoomUserService;
import com.tht.api.app.service.ChatService;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ChatFacadeTest {

    @Mock
    ChatService chatService;

    @Mock
    ChatRoomUserService chatRoomUserService;

    @InjectMocks
    ChatFacade chatFacade;

    @Test
    @DisplayName("채팅 내역 response 변환 테스트")
    void convertReadChatHistory() {

        ChatHistory entity = ChatHistory.of(1, "sender", "uuid", "msg", "imgUrl");
        when(chatService.readHistory(anyLong(), anyString(), anyInt()))
            .thenReturn(List.of(entity));

        //when
        List<ChatResponse> chatResponses = chatFacade.readHistory(1, "", 100);

        //then
        assertThat(chatResponses.get(0).chatIdx()).isEqualTo(entity.getId());
        assertThat(chatResponses.get(0).sender()).isEqualTo(entity.getSenderName());
        assertThat(chatResponses.get(0).senderUuid()).isEqualTo(entity.getSenderUuid());
        assertThat(chatResponses.get(0).msg()).isEqualTo(entity.getMsg());
        assertThat(chatResponses.get(0).imgUrl()).isEqualTo(entity.getImgUrl());
        assertThat(chatResponses.get(0).dateTime()).isEqualTo(entity.getCreatedAt());
    }

    @Test
    @DisplayName("나의 채팅방 리스트 조회 인수 테스트")
    void takeOverFindMyRoom() {

        //given
        String userUuid = "userUuid";

        //when
        chatFacade.findMyRoom(userUuid);

        //then
        verify(chatRoomUserService).findMyChatRoomPreviewInfo(userUuid);
        verify(chatService).findAllCurrentMessageIn(List.of());
    }
}