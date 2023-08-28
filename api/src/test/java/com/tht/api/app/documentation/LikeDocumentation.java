package com.tht.api.app.documentation;

import static com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.tht.api.app.controller.LikeController;
import com.tht.api.app.facade.user.UserLikeFacade;
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
    UserLikeFacade userLikeFacade;

    @DisplayName("유저 좋아요 api docs")
    @WithCustomMockUser
    @Test
    void doLikeAnotherUserDocs() throws Exception {

        String userUuid = "상대방 uuid";
        long dailyTopicIdx = 154;

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
                        .responseFields()
                        .build()
                )
            )
        ).andExpect(MockMvcResultMatchers.status().isCreated());
    }
}