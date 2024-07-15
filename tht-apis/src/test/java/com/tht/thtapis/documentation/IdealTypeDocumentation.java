package com.tht.thtapis.documentation;

import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.BDDMockito.when;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import java.util.List;

import com.tht.thtapis.controller.config.ControllerTestConfig;
import com.tht.thtapis.facade.idealtype.IdealTypeFacade;
import com.tht.thtapis.fixture.meta.IdealTypeFixture;
import com.tht.thtapis.ui.IdealTypeController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(IdealTypeController.class)
class IdealTypeDocumentation extends ControllerTestConfig {

    private static final String DEFAULT_URL = "/ideal-types";

    @MockBean
    IdealTypeFacade idealTypeFacade;

    @Test
    @DisplayName("이상형 목록 리스트 조회 api - docs")
    void getInterestAll() throws Exception {

        //given
        when(idealTypeFacade.getIdealTypeList()).thenReturn(
            List.of(IdealTypeFixture.responseMake()));

        //then
        ResultActions resultActions = ControllerTestConfig.mockMvc.perform(
            RestDocumentationRequestBuilders.get(DEFAULT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(
            MockMvcRestDocumentationWrapper.document("ideal-types-docs",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(
                    ResourceSnippetParameters.builder()
                        .tag("이상형 목록")
                        .description("이상형 목록 리스트 조회")
                        .requestFields()
                        .responseFields(
                                fieldWithPath("[].idx").description("idx"),
                                fieldWithPath("[].name").description("이상형 명칭"),
                                fieldWithPath("[].emojiCode").description("이모티콘 코드")
                        )
                        .responseSchema(Schema.schema("IdealTypeResponse"))
                        .build()
                )
            )
        );

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }
}