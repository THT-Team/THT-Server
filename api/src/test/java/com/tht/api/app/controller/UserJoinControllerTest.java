package com.tht.api.app.controller;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.when;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.tht.api.app.facade.join.UserJoinFacade;
import com.tht.api.app.facade.join.response.AuthNumberResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(UserJoinController.class)
class UserJoinControllerTest extends ControllerTestConfig {

    @MockBean
    UserJoinFacade userJoinFacade;

    @Test
    @DisplayName("유저 번호 인증 발급 docs")
    void getCertificationNumber() throws Exception {

        //given
        String phoneNumber = "01012345678";
        when(userJoinFacade.issueAuthenticationNumber(anyString())).thenReturn(
            new AuthNumberResponse("01012345678", 314215));

        //then
        ResultActions resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders.get("/user/certification/phone-number/{phone-number}",
                    phoneNumber)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(
            document("user-certification-number-docs",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(
                    ResourceSnippetParameters.builder()
                        .tag("유저")
                        .description("유저 번호 인증번호 발급")
                        .pathParameters(parameterWithName("phone-number").description("유저 폰 번호"))
                        .requestFields()
                        .responseFields(
                            fieldWithPath("phoneNumber").description("핸드폰 번호"),
                            fieldWithPath("authNumber").description("인코딩 된 인증번호")
                        )
                        .responseSchema(Schema.schema("AuthNumberResponse"))
                        .build()
                )
            ));

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());

    }
}