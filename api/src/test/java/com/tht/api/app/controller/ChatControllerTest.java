package com.tht.api.app.controller;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.tht.api.app.controller.config.ControllerTestConfig;
import com.tht.api.app.controller.config.WithCustomMockUser;
import com.tht.api.app.facade.chat.ChatFacade;
import com.tht.api.app.fixture.chat.ChatHistoryResponseFixture;
import com.tht.api.app.fixture.chat.ChatRoomResponseFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(ChatController.class)
class ChatControllerTest extends ControllerTestConfig {

    @MockBean
    ChatFacade chatFacade;

    @Test
    @DisplayName("채팅 내역 리스트 조회 api test - docs")
    void getChatHistory() throws Exception {

        //given
        when(chatFacade.readHistory(anyLong(), anyString(), anyInt()))
            .thenReturn(ChatHistoryResponseFixture.makeList());

        //then
        ResultActions resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders.get("/chat/history")
                .queryParam("roomNo", "1")
                .queryParam("chatIdx", ChatHistoryResponseFixture.getChatIdx())
                .queryParam("size", "100")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(
            document("채팅",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(
                    ResourceSnippetParameters.builder()
                        .tag("채팅")
                        .description("이전 채팅 내역 조회")
                        .queryParameters(
                            parameterWithName("roomNo").description("채팅방 idx"),
                            parameterWithName("chatIdx").optional()
                                .description("마지막 채팅 idx [null 일 경우 가장 최근 메세지 부터 조회]"),
                            parameterWithName("size").description("불러올 크기")
                        )
                        .requestFields()
                        .responseFields(
                            fieldWithPath("[].chatIdx").description("채팅"),
                            fieldWithPath("[].sender").description("보내는사람 이름"),
                            fieldWithPath("[].senderUuid").description("보내는사람 uuid"),
                            fieldWithPath("[].msg").description("메세지 내용"),
                            fieldWithPath("[].imgUrl").description("이미지 url"),
                            fieldWithPath("[].dateTime").description("보낸 시간")
                        )
                        .responseSchema(Schema.schema("ChatResponse"))
                        .build()
                )
            ));

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithCustomMockUser
    @DisplayName("유저 채팅방 리스트 조회 api test - docs")
    void getChatRooms() throws Exception {

        //given
        when(chatFacade.findMyRoomList(anyString())).thenReturn(ChatRoomResponseFixture.makeList());

        //then
        ResultActions resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders.get("/chat/rooms")
                .header("Authorization", "Bearer {ACCESS_TOKEN}")
        ).andDo(
            document("채팅",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(
                    ResourceSnippetParameters.builder()
                        .tag("채팅")
                        .description("로그인한 유저가 포함된 채팅방 리스트 조회")
                        .requestFields()
                        .responseFields(
                            fieldWithPath("[].chatRoomIdx").description("채팅방 idx"),
                            fieldWithPath("[].partnerName").description("채팅 상대방 이름"),
                            fieldWithPath("[].partnerProfileUrl").description("채팅 상대방 프로필 사진 url"),
                            fieldWithPath("[].currentMessage").description("가장 최근 메세지 내용"),
                            fieldWithPath("[].messageTime").description("메세지 전송 시간")
                        )
                        .responseSchema(Schema.schema("ChatRoomResponse"))
                        .build()
                )
            ));

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }
}