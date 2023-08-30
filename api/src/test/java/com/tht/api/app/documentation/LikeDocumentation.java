package com.tht.api.app.documentation;

import static com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.anyLong;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.tht.api.app.controller.LikeController;
import com.tht.api.app.facade.like.LikeFacade;
import com.tht.api.app.facade.like.response.LikeReceiveResponse;
import com.tht.api.app.facade.like.response.LikeResponse;
import com.tht.api.app.unit.controller.config.ControllerTestConfig;
import com.tht.api.app.unit.controller.config.WithCustomMockUser;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(LikeController.class)
class LikeDocumentation extends ControllerTestConfig {

    @MockBean
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

        mockMvc.perform(
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
                        .description("마음에 드는 유저 좋아요 버튼 클릭 api")
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

        mockMvc.perform(
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
                            fieldWithPath("[].dailyFallingIdx").description("좋아요를 받았던 시점의 데일리 토픽 idx"),
                            fieldWithPath("[].likeIdx").description("좋아요 idx"),
                            fieldWithPath("[].topic").description("좋아요를 받았던 시점의 데일리 토픽 주제"),
                            fieldWithPath("[].issue").description("좋아요를 받았던 시점의 데일리 토픽 이슈 내용"),
                            fieldWithPath("[].userUuid").description("유저 uuid"),
                            fieldWithPath("[].username").description("유저 이름"),
                            fieldWithPath("[].profileUrl").description("프로필 사진 url"),
                            fieldWithPath("[].age").description("나이"),
                            fieldWithPath("[].address").description("주소"),
                            fieldWithPath("[].receivedTime").description("좋아요를 받은 시간 [yyyy-MM-dd HH:mm:ss]")
                        )
                        .responseSchema(Schema.schema("LikeReceiveResponse"))
                        .build()
                )
            )
        ).andExpect(MockMvcResultMatchers.status().isOk());

    }
}