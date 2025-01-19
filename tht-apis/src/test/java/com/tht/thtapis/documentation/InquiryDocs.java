package com.tht.thtapis.documentation;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.tht.thtapis.controller.config.ControllerTestConfig;
import com.tht.thtapis.facade.setting.InquiryRequest;
import com.tht.thtapis.facade.user.InquiryFacade;
import com.tht.thtapis.fixture.setting.InquiryRequestFixture;
import com.tht.thtapis.ui.InquiryController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InquiryController.class)
class InquiryDocs extends ControllerTestConfig {

    @MockitoBean
    InquiryFacade inquiryFacade;

    @Test
    @DisplayName("로그인 및 회원가입 전 문의하기 docs")
    void inquiryDocs() throws Exception {

        InquiryRequest request = InquiryRequestFixture.make();
        String requestBody = ControllerTestConfig.objectMapper.writeValueAsString(request);

        final ResultActions resultActions = mockMvc.perform(post("/users/login/inquiry")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(requestBody)
        ).andDo(
            document("로그인 및 회원가입 전 문의하기",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(
                    ResourceSnippetParameters.builder()
                        .tag("유저 - 로그인")
                        .description("로그인 및 회원가입 전 문의하기")
                        .requestFields(
                            fieldWithPath("userEmail").description("문의 답장 받을 이메일"),
                            fieldWithPath("contents").description("유저 디바이스 키"),
                            fieldWithPath("isEmailAgree").description("메일 정보동의 제공 여부")
                        )
                        .requestSchema(Schema.schema("InquiryRequest"))
                        .build()
                ))
        );

        resultActions.andExpect(status().isCreated());

    }
}
