package com.tht.api.app.controller;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.tht.api.app.controller.config.ControllerTestConfig;
import com.tht.api.app.facade.user.UserLoginFacade;
import com.tht.api.app.facade.user.request.UserLoginRequest;
import com.tht.api.app.facade.user.response.UserLoginResponse;
import com.tht.api.app.fixture.user.UserLoginRequestFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(UserLoginController.class)
class UserLoginControllerTest extends ControllerTestConfig {

    private static final String DEFAULT_URL = "/users/login";

    @MockBean
    UserLoginFacade userLoginFacade;

    @Test
    @DisplayName("유저 일반 회원가입  api test - docs (성공)")
    void normalUserLogin() throws Exception {

        //give
        UserLoginRequest request = UserLoginRequestFixture.make();
        String requestBody = objectMapper.writeValueAsString(request);

        when(userLoginFacade.login(any())).thenReturn(new UserLoginResponse("token", 1L));

        //then
        ResultActions resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders.post(DEFAULT_URL + "/normal")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestBody)
        ).andDo(
            document("유저 일반 로그인 docs",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(
                    ResourceSnippetParameters.builder()
                        .tag("유저")
                        .description("유저 일반 로그인")
                        .requestFields(
                            fieldWithPath("phoneNumber").description("유저 전화번호"),
                            fieldWithPath("deviceKey").description("유저 디바이스 키")
                        )
                        .responseFields(
                            fieldWithPath("accessToken").description("액세스 토큰"),
                            fieldWithPath("accessTokenExpiresIn").description("액세스 토큰 만료시간")
                        )
                        .responseSchema(Schema.schema("UserLoginResponse"))
                        .requestSchema(Schema.schema("UserLoginRequest"))
                        .build()
                ))
        );

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());

    }

}