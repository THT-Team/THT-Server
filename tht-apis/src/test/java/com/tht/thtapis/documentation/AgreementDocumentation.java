package com.tht.thtapis.documentation;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.tht.enums.agreement.AgreementCategory;
import com.tht.thtapis.controller.config.ControllerTestConfig;
import com.tht.thtapis.controller.config.WithCustomMockUser;
import com.tht.thtapis.facade.user.UserFacade;
import com.tht.thtapis.fixture.user.UserAgreementUpdateRequestFixture;
import com.tht.thtapis.ui.UserController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.patch;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

@WebMvcTest(UserController.class)
public class AgreementDocumentation extends ControllerTestConfig {

    @MockBean
    UserFacade userFacade;

    @Test
    @WithCustomMockUser
    @DisplayName("유저 약관동의 내역 수정 api documentation")
    void patchUserAgreement() throws Exception {

        //give
        String requestBody = ControllerTestConfig.objectMapper.writeValueAsString(UserAgreementUpdateRequestFixture.makeJson());

        ResultActions resultActions = ControllerTestConfig.mockMvc.perform(patch("/user/agreement")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer {ACCESS_TOKEN}")
                        .content(requestBody))
                .andDo(document("유저 약관동의 내역 수정 api",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(ResourceSnippetParameters.builder()
                                .tag("마이페이지")
                                .description("유저의 약관동의 내역 수정")
                                .requestFields(
                                        fieldWithPath("agreementName").type(JsonFieldType.STRING).description(String.format("약관동의 내역 카테고리 변수명 ex - %s", EnumDocsUtils.getTypesFieldList(AgreementCategory.class))),
                                        fieldWithPath("value").type(JsonFieldType.BOOLEAN).description("[true, false] 수정 동의 결과값")
                                )
                                .responseFields()
                                .build())
                ));

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }
}
