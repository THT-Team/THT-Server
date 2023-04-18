package com.tht.api.app.controller;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tht.api.app.facade.user.UserFacade;
import com.tht.api.app.facade.user.response.AuthNumberResponse;
import com.tht.api.app.facade.user.response.UserNickNameValidResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(UserController.class)
class UserControllerTest extends ControllerTestConfig {

    @MockBean
    UserFacade userFacade;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("유저 번호 인증 발급 docs")
    void getCertificationNumber() throws Exception {

        //given
        String phoneNumber = "01012345678";
        when(userFacade.issueAuthenticationNumber(anyString())).thenReturn(
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

    @Test
    @DisplayName("유저 닉네인 중복체크 api test (성공)")
    void duplicateNickname() throws Exception {

        //given
        String nickName = "test 닉네임";
        when(userFacade.checkDuplicateNickName(anyString()))
            .thenReturn(new UserNickNameValidResponse(true));

        //then
        ResultActions resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders.get("/user/nick-name/duplicate-check/{nick-name}",
                    nickName)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(
            document("user-duplicate-nickname-docs",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(
                    ResourceSnippetParameters.builder()
                        .tag("유저")
                        .description("유저 닉네임 중복 체크")
                        .pathParameters(parameterWithName("nick-name").description("닉네임"))
                        .requestFields()
                        .responseFields(
                            fieldWithPath("isDuplicate").description("중복 여부")
                        )
                        .responseSchema(Schema.schema("UserNicknameValidResponse"))
                        .build()
                )
            ));

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());

    }
}
