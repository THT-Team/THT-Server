package com.tht.api.app.facade.chat;

import static org.mockito.BDDMockito.anyInt;
import static org.mockito.BDDMockito.anyLong;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.when;

import com.tht.api.app.entity.ChatHistory;
import com.tht.api.app.facade.chat.response.ChatResponse;
import com.tht.api.app.service.ChatService;
import java.util.List;
import org.assertj.core.api.Assertions;
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

    @InjectMocks
    ChatFacade chatFacade;

    @Test
    @DisplayName("채팅 내역 reponse 변환 테스트")
    void convertReadChatHistory() {

        ChatHistory entity = ChatHistory.of(1, "sender", "uuid", "msg", "imgUrl");
        when(chatService.readHistory(anyLong(), anyString(), anyInt()))
            .thenReturn(List.of(entity));

        //when
        List<ChatResponse> chatResponses = chatFacade.readHistory(1, "", 100);

        //then
        Assertions.assertThat(chatResponses.get(0).chatIdx()).isEqualTo(entity.getId());
        Assertions.assertThat(chatResponses.get(0).sender()).isEqualTo(entity.getSenderName());
        Assertions.assertThat(chatResponses.get(0).senderUuid()).isEqualTo(entity.getSenderUuid());
        Assertions.assertThat(chatResponses.get(0).msg()).isEqualTo(entity.getMsg());
        Assertions.assertThat(chatResponses.get(0).imgUrl()).isEqualTo(entity.getImgUrl());
        Assertions.assertThat(chatResponses.get(0).dateTime()).isEqualTo(entity.getCreatedAt());
    }
}