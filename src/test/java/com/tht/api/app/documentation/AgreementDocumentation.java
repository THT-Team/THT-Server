package com.tht.api.app.documentation;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.tht.api.app.controller.UserController;
import com.tht.api.app.controller.config.ControllerTestConfig;
import com.tht.api.app.controller.config.WithCustomMockUser;
import com.tht.api.app.entity.enums.AgreementCategory;
import com.tht.api.app.facade.user.UserFacade;
import com.tht.api.app.facade.user.request.UserAgreementUpdateRequest;
import com.tht.api.app.fixture.user.UserAgreementUpdateRequestFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
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
        UserAgreementUpdateRequest request = UserAgreementUpdateRequestFixture.make();
        String requestBody = objectMapper.writeValueAsString(request);

        ResultActions resultActions = mockMvc.perform(patch("/user/agreement")
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
