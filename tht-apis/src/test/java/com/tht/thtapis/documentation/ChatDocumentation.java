package com.tht.thtapis.documentation;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.tht.thtapis.controller.config.ControllerTestConfig;
import com.tht.thtapis.controller.config.WithCustomMockUser;
import com.tht.thtapis.facade.chat.ChatFacade;
import com.tht.thtapis.fixture.chat.ChatHistoryResponseFixture;
import com.tht.thtapis.fixture.chat.ChatRoomResponseFixture;
import com.tht.thtapis.ui.ChatController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(ChatController.class)
class ChatDocumentation extends ControllerTestConfig {

    @MockBean
    ChatFacade chatFacade;

    @Test
    @DisplayName("채팅 내역 리스트 조회 api - docs")
    void getChatHistory() throws Exception {

        //given
        when(chatFacade.readHistory(anyLong(), anyString(), anyInt()))
            .thenReturn(ChatHistoryResponseFixture.makeList());

        //then
        ResultActions resultActions = ControllerTestConfig.mockMvc.perform(
            get("/chat/history")
                .queryParam("roomNo", "1")
                .queryParam("chatIdx", ChatHistoryResponseFixture.getChatIdx())
                .queryParam("size", "100")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(
            document("채팅 내역 리스트 조회 docs",
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
    @DisplayName("유저 채팅방 리스트 조회 api - docs")
    void getChatRooms() throws Exception {

        //given
        when(chatFacade.findMyRoomList(anyString())).thenReturn(ChatRoomResponseFixture.makePreviewResponseList());

        //then
        ResultActions resultActions = ControllerTestConfig.mockMvc.perform(
            get("/chat/rooms")
                .header("Authorization", "Bearer {ACCESS_TOKEN}")
        ).andDo(
            document("유저 채팅방 리스트 조회 docs",
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
                            fieldWithPath("[].messageTime").description("메세지 전송 시간"),
                            fieldWithPath("[].isAvailableChat").description("채팅방 활성화 여부")
                        )
                        .responseSchema(Schema.schema("ChatRoomResponse"))
                        .build()
                )
            ));

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }


    @Test
    @WithCustomMockUser
    @DisplayName("유저 채팅방 나가기 api - docs")
    void outChatRoom() throws Exception {

        //given
        long chatRoomIdx = 13;

        //then
        ResultActions resultActions = ControllerTestConfig.mockMvc.perform(
            post("/chat/out/room/{chat-room-idx}", chatRoomIdx)
                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(
            document("채팅방 나가기 docs",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(
                    ResourceSnippetParameters.builder()
                        .tag("채팅")
                        .description("채팅방 나가기")
                        .pathParameters(parameterWithName("chat-room-idx").description("채팅방 idx"))
                        .requestFields()
                        .responseFields()
                        .build()
                )
            ));

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithCustomMockUser
    @DisplayName("유저 채팅방 상세조회 api - docs")
    void getDetailRoomInfo() throws Exception {

        //given
        long chatRoomIdx = 13;
        when(chatFacade.findMyRoomDetail(anyLong(), anyString())).thenReturn(ChatRoomResponseFixture.make());

        //then
        ResultActions resultActions = ControllerTestConfig.mockMvc.perform(
            get("/chat/room/{chat-room-idx}", chatRoomIdx)
                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(
            document("채팅방 상세조회 docs",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(
                    ResourceSnippetParameters.builder()
                        .tag("채팅")
                        .description("채팅방 정보 상세 조회")
                        .pathParameters(parameterWithName("chat-room-idx").description("채팅방 idx"))
                        .requestFields()
                        .responseFields(
                            fieldWithPath("chatRoomIdx").description("채팅방 idx"),
                            fieldWithPath("talkSubject").description("대화 주제"),
                            fieldWithPath("talkIssue").description("대화 주제 파생질문"),
                            fieldWithPath("startDate").description("재화 시작일"),
                            fieldWithPath("isChatAble").description("채팅 가능여부")
                        )
                        .build()
                )
            ));

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }
}