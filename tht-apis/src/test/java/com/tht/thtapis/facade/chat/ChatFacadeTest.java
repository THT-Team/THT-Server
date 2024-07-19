package com.tht.thtapis.facade.chat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.anyInt;
import static org.mockito.BDDMockito.anyLong;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.when;
import static org.mockito.Mockito.verify;

import java.time.format.DateTimeFormatter;
import java.util.List;

import com.tht.infra.chat.ChatHistory;
import com.tht.infra.chat.group.ChatRoomMapperGroup;
import com.tht.thtapis.facade.chat.response.ChatResponse;
import com.tht.thtapis.facade.chat.response.ChatRoomPreviewResponse;
import com.tht.thtapis.facade.chat.response.ChatRoomResponse;
import fixture.chat.ChatRoomMapperFixture;
import fixture.chat.ChatRoomPreviewMapperFixture;
import com.tht.infra.chat.service.ChatRoomService;
import com.tht.infra.chat.service.ChatRoomUserService;
import com.tht.infra.chat.service.ChatService;
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
    @Mock
    ChatRoomService chatRoomService;

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
        chatFacade.findMyRoomList(userUuid);

        //then
        verify(chatRoomUserService).findMyChatRoomPreviewInfo(userUuid);
        verify(chatService).findAllCurrentMessageIn(List.of());
    }

    @Test
    @DisplayName("채팅방 상세조회 반환값 테스트")
    void returnResponseTypeAtFindRoomDetail() {

        ChatRoomMapperGroup group = ChatRoomMapperGroup.of(List.of(ChatRoomMapperFixture.make()));

        when(chatRoomService.findDetailInfoById(anyLong(), anyString()))
            .thenReturn(group);

        ChatRoomResponse response = chatFacade.findMyRoomDetail(1, "userUuid");

        assertThat(response.chatRoomIdx()).isEqualTo(group.getBasic().chatRoomIdx());
        assertThat(response.talkIssue()).isEqualTo(group.getBasic().talkIssue());
        assertThat(response.talkSubject()).isEqualTo(group.getBasic().talkSubject());
        assertThat(response.startDate()).isEqualTo(group.getBasic().startDate().format(
            DateTimeFormatter.ofPattern("y년 M월 d일")));
        assertThat(response.isChatAble()).isTrue();
    }

    @Test
    @DisplayName("나의 채팅방 조회시 매칭된 채팅방이 최근 대화 수 보다 많을 때")
    void RoomMoreThanMessage() {

        //given
        when(chatRoomUserService.findMyChatRoomPreviewInfo(anyString())).thenReturn(
            List.of(
                ChatRoomPreviewMapperFixture.make(1L),
                ChatRoomPreviewMapperFixture.make(2L),
                ChatRoomPreviewMapperFixture.make(3L),
                ChatRoomPreviewMapperFixture.make(4L),
                ChatRoomPreviewMapperFixture.make(5L)
            )
        );

        when(chatService.findAllCurrentMessageIn(anyList())).thenReturn(
            List.of()
        );

        //when
        List<ChatRoomPreviewResponse> result = chatFacade.findMyRoomList("user-uuid");

        //then
        assertThat(result).hasSize(5);
        assertThat(result).extracting("currentMessage").containsOnly("매칭된 무디와 먼저 대화를 시작해 보세요.");

    }
}