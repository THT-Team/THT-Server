package com.tht.api.app.documentation;

import static com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.BDDMockito.anyLong;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.tht.api.app.controller.LikeController;
import com.tht.api.app.facade.like.LikeFacade;
import com.tht.api.app.facade.like.response.LikeResponse;
import com.tht.api.app.unit.controller.config.ControllerTestConfig;
import com.tht.api.app.unit.controller.config.WithCustomMockUser;
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
                            fieldWithPath("isMatching").description("상대방도 나를 좋아하는 상태라면 매칭 성공 (true)"),
                            fieldWithPath("chatRoomIdx").description("isMatching 이 true 인 경우 생성된 채팅방 idx, false 면 null")
                        )
                        .responseSchema(Schema.schema("LikeResponse"))
                        .build()
                )
            )
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }
}