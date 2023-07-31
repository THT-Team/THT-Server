package com.tht.api.app.controller;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.tht.api.app.controller.config.ControllerTestConfig;
import com.tht.api.app.facade.interestTest.InterestTestFacade;
import com.tht.api.app.facade.interestTest.response.InterestTestResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

@WebMvcTest(InterestTestController.class)
class InterestTestControllerTest extends ControllerTestConfig {

    private static final String DEFAULT_URL = "/interestTest";

    @MockBean
    InterestTestFacade interestTestFacade;

    @Test
    @DisplayName("관심사 목록 조회 테스트")
    void getInterestTestAll() throws Exception{

        //given
        when(interestTestFacade.getInterestTestList()).thenReturn(
                List.of(new InterestTestResponse(1, "이상형 명칭"))
        );

        //then
        ResultActions resultActions = mockMvc.perform(
                RestDocumentationRequestBuilders.get(DEFAULT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                MockMvcRestDocumentationWrapper.document("interestTest-docs",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    resource(
                            ResourceSnippetParameters.builder()
                                    .tag("관심사 목록 테스트")
                                    .description("관심사목록 테스트 리스트 조회")
                                    .requestFields()
                                    .responseFields(
                                            fieldWithPath("[].idx").description("idx"),
                                            fieldWithPath("[].name").description("이상형 명칭")
                                    )
                                    .responseSchema(Schema.schema("InterestTestResponse"))
                                    .build()
                    )
            )
        );

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }
}