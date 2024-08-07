package com.tht.domain.chat.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.anyLong;
import static org.mockito.BDDMockito.when;

import java.util.List;

import com.tht.domain.entity.chat.group.ChatRoomMapperGroup;
import com.tht.domain.entity.chat.repository.ChatRoomRepository;
import com.tht.domain.entity.chat.service.ChatRoomService;
import com.tht.domain.exception.EntityStateException;
import fixture.chat.ChatRoomMapperFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ChatRoomServiceTest {

    @Mock
    ChatRoomRepository chatRoomRepository;

    @InjectMocks
    ChatRoomService chatRoomService;

    @Test
    @DisplayName("채팅방 상세조회 return 값 테스트")
    void findDetail() {

        //give
        when(chatRoomRepository.findMyChatRoomInfos(anyLong(), anyString()))
            .thenReturn(List.of(ChatRoomMapperFixture.make()));

        //when
        ChatRoomMapperGroup group = chatRoomService.findDetailInfoById(1L, "userUuid");

        //then
        assertThat(group.getBasic().getClass().getSimpleName()).isEqualTo("ChatRoomMapper");
    }

    @Test
    @DisplayName("채팅방이 존재하지 않을 때 exception")
    void doNotExist() {

        when(chatRoomRepository.existsById(anyLong())).thenReturn(false);

        assertThatThrownBy(() -> chatRoomService.existBy(1))
            .isInstanceOf(EntityStateException.class)
            .hasMessageContaining("해당 겂에 해당하는 ChatRoom 가(이) 존재하지 않습니다.");
    }
}