package com.tht.thtapis.documentation;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.tht.thtapis.controller.config.ControllerTestConfig;
import com.tht.thtapis.controller.config.WithCustomMockUser;
import com.tht.thtapis.facade.like.LikeFacade;
import com.tht.thtapis.facade.like.response.LikeReceiveResponse;
import com.tht.thtapis.facade.like.response.LikeResponse;
import com.tht.thtapis.ui.LikeController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.List;

import static com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

@WebMvcTest(LikeController.class)
class LikeDocumentation extends ControllerTestConfig {

    @MockitoBean
    LikeFacade likeFacade;

    @DisplayName("유저 좋아요 api docs")
    @WithCustomMockUser
    @Test
    void doLikeAnotherUserDocs() throws Exception {

        //given
        String userUuid = "상대방 uuid";
        long dailyTopicIdx = 154;

        //given
        LikeResponse response = new LikeResponse(true, 34L);
        when(likeFacade.doLike(anyString(), anyString(), anyLong())).thenReturn(response);

        ControllerTestConfig.mockMvc.perform(
            post("/i-like-you/{favorite-user-uuid}/{daily-topic-idx}", userUuid, dailyTopicIdx)
                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(
            document("유저 좋아요 docs",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(
                    ResourceSnippetParameters.builder()
                        .tag("좋아요")
                        .description("메인화면에 표출되는 마음에 드는 유저 좋아요 버튼 클릭 api")
                        .pathParameters(
                            parameterWithName("favorite-user-uuid").description("상대방 uuid"),
                            parameterWithName("daily-topic-idx").description("매칭된 토픽 idx")
                        )
                        .requestFields()
                        .responseFields(
                            fieldWithPath("isMatching").description(
                                "상대방도 나를 좋아하는 상태라면 매칭 성공 (true)"),
                            fieldWithPath("chatRoomIdx").description(
                                "isMatching 이 true 인 경우 생성된 채팅방 idx, false 면 null")
                        )
                        .responseSchema(Schema.schema("LikeResponse"))
                        .build()
                )
            )
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @DisplayName("유저 싫어요 api docs")
    @WithCustomMockUser
    @Test
    void dontLikeAnotherUserDocs() throws Exception {

        //given
        String userUuid = "상대방 uuid";
        long dailyTopicIdx = 154;

        ControllerTestConfig.mockMvc.perform(
            post("/i-dont-like-you/{dont-favorite-user-uuid}/{daily-topic-idx}", userUuid, dailyTopicIdx)
                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(
            document("유저 싫어요 docs",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(
                    ResourceSnippetParameters.builder()
                        .tag("좋아요")
                        .description("메인화면에 표출되는 마음에 들지 않는 유저 싫어요 버튼 클릭 api")
                        .pathParameters(
                            parameterWithName("dont-favorite-user-uuid").description("상대방 uuid"),
                            parameterWithName("daily-topic-idx").description("매칭된 토픽 idx")
                        )
                        .requestFields()
                        .responseFields()
                        .build()
                )
            )
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @DisplayName("내가 받은 좋아요 리스트 api docs")
    @WithCustomMockUser
    @Test
    void myReceivedLikeList() throws Exception {

        //given
        when(likeFacade.getLikedPeopleList(anyString(), anyInt(), anyLong(), anyLong())).thenReturn(
            List.of(
                LikeReceiveResponse.of(1, 1, "행복", "무엇을 할때 행복한가요?", "user-uuid-1", "유저1", "profile-url", 24, "주소", LocalDateTime.now()),
                LikeReceiveResponse.of(2, 3,"취미", "침대에 누워있고 싶지 않으세요?", "user-uuid-2", "유저2", "profile-url", 32, "주소", LocalDateTime.now()),
                LikeReceiveResponse.of(3, 154, "평화", "돈많은 백수가 되고싶네요", "user-uuid-3","유저3", "profile-url", 64, "주소", LocalDateTime.now()),
                LikeReceiveResponse.of(2, 893, "취미", "침대에 누워있고 싶지 않으세요?", "user-uuid-4", "유저4", "profile-url", 27, "주소", LocalDateTime.now())
            ));

        ControllerTestConfig.mockMvc.perform(
            get("/like/receives")
                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .queryParam("size", "100")
                .queryParam("lastFallingTopicIdx", "3")
                .queryParam("lastFallingTopicIdx", "3")
                .queryParam("lastLikeIdx", "1341")
        ).andDo(
            document("내가 받은 좋아요 리스트 docs",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(
                    ResourceSnippetParameters.builder()
                        .tag("좋아요")
                        .description("내가 받은 좋아요 리스트 조회 api")
                        .queryParameters(
                            parameterWithName("size").description("페이징 사이즈 [default : 100]"),
                            parameterWithName("lastFallingTopicIdx").description("조회된 리스트 마지막 좋아요의 토픽 idx (null 일경우 최근부터 조회)"),
                            parameterWithName("lastLikeIdx").description("조회된 리스트의 마지막 좋아요 idx (null 일경우 최근부터 조회)")
                        )
                        .requestFields()
                        .responseFields(
                            fieldWithPath("likeList").description("나를 좋아하는 유저 리스트"),
                            fieldWithPath("likeList[].dailyFallingIdx").description("좋아요를 받았던 시점의 데일리 토픽 idx"),
                            fieldWithPath("likeList[].likeIdx").description("좋아요 idx"),
                            fieldWithPath("likeList[].topic").description("좋아요를 받았던 시점의 데일리 토픽 주제"),
                            fieldWithPath("likeList[].issue").description("좋아요를 받았던 시점의 데일리 토픽 이슈 내용"),
                            fieldWithPath("likeList[].userUuid").description("유저 uuid"),
                            fieldWithPath("likeList[].username").description("유저 이름"),
                            fieldWithPath("likeList[].profileUrl").description("프로필 사진 url"),
                            fieldWithPath("likeList[].age").description("나이"),
                            fieldWithPath("likeList[].address").description("주소"),
                            fieldWithPath("likeList[].receivedTime").description("좋아요를 받은 시간 [yyyy-MM-dd HH:mm:ss]"),

                            fieldWithPath("size").description("유저 리스트 사이즈"),
                            fieldWithPath("lastFallingTopicIdx").description("마지막 요소의 daily Topic idx 커서 값 [빈 리스트 일 경우 null]"),
                            fieldWithPath("lastLikeIdx").description("마자막 요소의 idx 커서 값 [빈 리스트 일 경우 null]")
                        )
                        .responseSchema(Schema.schema("LikeReceiveResponse"))
                        .build()
                )
            )
        ).andExpect(MockMvcResultMatchers.status().isOk());

    }

    @DisplayName("좋아요 거절하기 api docs")
    @WithCustomMockUser
    @Test
    void rejectLikeDocs() throws Exception {

        //given
        ControllerTestConfig.mockMvc.perform(
            post("/like/reject")
                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .queryParam("likeIdx", "1234")
        ).andDo(
            document("좋아요 거절하기 docs",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(
                    ResourceSnippetParameters.builder()
                        .tag("좋아요")
                        .description("좋아요 거절하기 api")
                        .queryParameters(
                            parameterWithName("likeIdx").description("좋아요 idx")
                        )
                        .build()
                )
            )
        ).andExpect(MockMvcResultMatchers.status().isOk());

    }
}