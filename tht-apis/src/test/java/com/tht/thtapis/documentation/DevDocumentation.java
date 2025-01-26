package com.tht.thtapis.documentation;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.tht.domain.entity.like.UserLikeRepository;
import com.tht.domain.entity.user.repository.UserDailyFallingRepository;
import com.tht.thtapis.controller.config.ControllerTestConfig;
import com.tht.thtapis.dev.DevController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.headers.HeaderDocumentation;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;

@WebMvcTest(DevController.class)
class DevDocumentation extends ControllerTestConfig {

    @MockitoBean
    UserLikeRepository userLikeRepository;
    @MockitoBean
    UserDailyFallingRepository userDailyFallingRepository;

    @Test
    @DisplayName("유저 좋아요 싫어요 선택 데이터 초기화 api documentation")
    void resetLike() throws Exception {

        // Mock Repository 동작 정의
        doNothing().when(userLikeRepository).deleteAllByUserUuid(anyString());

        //give
        ResultActions resultActions = ControllerTestConfig.mockMvc.perform(post("/dev/like-and-dislike/reset")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer {ACCESS_TOKEN}")
            )
            .andDo(document("유저 좋아요 싫어요 선택 데이터 초기화 api",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                HeaderDocumentation.requestHeaders(
                    headerWithName("Authorization").description("Bearer {ACCESS_TOKEN}")
                ),
                resource(ResourceSnippetParameters.builder()
                    .tag("개발용")
                    .description("유저 좋아요 싫어요 선택 데이터 전부 삭제")
                    .responseFields()
                    .build())
            ));

        resultActions.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    @DisplayName("유저가 선택한 그날의 주제어 초기화 api documentation")
    void resetSelectedTalkKeyword() throws Exception {

        // Mock Repository 동작 정의
        doNothing().when(userDailyFallingRepository).deleteAllByUserUuid(anyString());

        //give
        ResultActions resultActions = ControllerTestConfig.mockMvc.perform(post("/dev/selected-talk-keywords/reset")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer {ACCESS_TOKEN}")
            )
            .andDo(document("유저가 선택한 그날의 주제어 초기화 api",

                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(ResourceSnippetParameters.builder()
                    .tag("개발용")
                    .description("유저가 선택한 그날의 주제어 데이터 전부 삭제")
                    .responseFields()
                    .build())
            ));

        resultActions.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

}
