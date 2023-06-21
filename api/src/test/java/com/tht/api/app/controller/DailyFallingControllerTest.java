package com.tht.api.app.controller;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.BDDMockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.tht.api.app.controller.config.ControllerTestConfig;
import com.tht.api.app.facade.main.DailyFallingFacade;
import com.tht.api.app.fixture.main.DailyFallingResponseFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(DailyFallingController.class)
class DailyFallingControllerTest extends ControllerTestConfig {

    @MockBean
    DailyFallingFacade dailyFallingFacade;

    @Test
    @DisplayName("오늘의 폴링 주제 리스트 조회 api - docs")
    void getDailyFallingList() throws Exception {

        //give
        when(dailyFallingFacade.getDailyFallingList()).thenReturn(DailyFallingResponseFixture.makeList());

        //then
        ResultActions resultActions = mockMvc.perform(
            get("/falling/daily-keyword")
                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(
            document("오늘의 폴링 주제 리스트 조회 docs",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(
                    ResourceSnippetParameters.builder()
                        .tag("메인")
                        .description("오늘의 폴링 주제 리스트 조회")
                        .requestFields()
                        .responseFields(
                            fieldWithPath("[].idx").description("폴링 주제어 idx"),
                            fieldWithPath("[].keyword").description("폴링 주제어"),
                            fieldWithPath("[].keywordImgUrl").description("폴링 주제어 이미지 url"),
                            fieldWithPath("[].talkIssue").description("주제어 파생 질문")
                        )
                        .build()
                )
            ));

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }
}