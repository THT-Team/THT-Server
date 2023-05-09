package com.tht.api.app.controller;

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.tht.api.app.controller.config.ControllerTestConfig;
import com.tht.api.app.facade.interest.InterestFacade;
import com.tht.api.app.facade.interest.response.InterestResponse;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(InterestController.class)
class InterestControllerTest extends ControllerTestConfig {

    private static final String DEFAULT_URL = "/interests";

    @MockBean
    InterestFacade interestFacade;

    @Test
    @DisplayName("관심사 목록 리스트 조회")
    void getInterestAll() throws Exception {

        //given
        when(interestFacade.getInterestList()).thenReturn(
            List.of(new InterestResponse(1, "이상형 명칭", "emoji code"))
        );

        //then
        ResultActions resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders.get(DEFAULT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(
            MockMvcRestDocumentationWrapper.document("interest-docs",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(
                    ResourceSnippetParameters.builder()
                        .tag("관심사 목록")
                        .description("관심사 목록 리스트 조회")
                        .requestFields()
                        .responseFields(
                            fieldWithPath("[].idx").description("idx"),
                            fieldWithPath("[].name").description("이상형 명칭"),
                            fieldWithPath("[].emojiCode").description("이모티콘 코드")
                        )
                        .responseSchema(Schema.schema("InterestResponse"))
                        .build()
                )
            )
        );

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }

}