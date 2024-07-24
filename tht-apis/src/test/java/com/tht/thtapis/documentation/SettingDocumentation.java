package com.tht.thtapis.documentation;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.tht.thtapis.controller.config.ControllerTestConfig;
import com.tht.thtapis.controller.config.WithCustomMockUser;
import com.tht.thtapis.facade.setting.InquiryRequest;
import com.tht.thtapis.facade.setting.SettingFacade;
import com.tht.thtapis.fixture.setting.InquiryRequestFixture;
import com.tht.thtapis.security.SecurityConst;
import com.tht.thtapis.ui.SettingController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SettingController.class)
class SettingDocumentation extends ControllerTestConfig {

    @MockBean
    SettingFacade settingFacade;

    @Test
    @WithCustomMockUser
    @DisplayName("문의하기 api docs")
    void docsInquiryApi() throws Exception {

        InquiryRequest request = InquiryRequestFixture.make();
        String requestBody = ControllerTestConfig.objectMapper.writeValueAsString(request);

        doNothing().when(settingFacade).inquiry(anyString(), any());

        final ResultActions resultActions = mockMvc.perform(post("/user/inquiry")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .header(SecurityConst.AUTH_HEADER_NAME, "Bearer {ACCESS_TOKEN}")
            .content(requestBody)
        ).andDo(
            document("문의하기 api docs",
                resource(
                    ResourceSnippetParameters.builder()
                        .tag("설정")
                        .description("유저 문의하기 api")
                        .requestFields(
                            fieldWithPath("userEmail").description("문의 답장 받을 이메일"),
                            fieldWithPath("contents").description("유저 디바이스 키"),
                            fieldWithPath("isEmailAgree").description("메일 정보동의 제공 여부")
                        )
                        .requestSchema(Schema.schema("InquiryRequest"))
                        .build()
                ))
        );

        resultActions.andExpect(status().isOk());

    }
}


