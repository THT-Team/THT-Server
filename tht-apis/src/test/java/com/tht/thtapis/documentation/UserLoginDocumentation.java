package com.tht.thtapis.documentation;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.tht.domain.entity.user.exception.UserTokenException;
import com.tht.thtapis.controller.config.ControllerTestConfig;
import com.tht.thtapis.fixture.TokenDtoFixture;
import com.tht.thtapis.fixture.user.UserLoginRequestFixture;
import com.tht.thtapis.fixture.user.UserSNSLoginRequestFixture;
import com.tht.thtapis.ui.UserLoginController;
import com.tht.thtapis.facade.user.UserLoginFacade;
import com.tht.thtapis.facade.user.request.UserLoginRequest;
import com.tht.thtapis.facade.user.request.UserSNSLoginRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

@WebMvcTest(UserLoginController.class)
class UserLoginDocumentation extends ControllerTestConfig {

    private static final String DEFAULT_URL = "/users/login";

    @MockitoBean
    UserLoginFacade userLoginFacade;

    @Test
    @DisplayName("유저 일반 로그인 docs")
    void normalUserLogin() throws Exception {

        //give
        UserLoginRequest request = UserLoginRequestFixture.make();
        String requestBody = ControllerTestConfig.objectMapper.writeValueAsString(request);

        when(userLoginFacade.login(any()))
            .thenReturn(TokenDtoFixture.createTokenDto());

        //then
        ResultActions resultActions = ControllerTestConfig.mockMvc.perform(
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
                        .tag("유저 - 로그인")
                        .description("유저 일반 로그인")
                        .requestFields(
                            fieldWithPath("phoneNumber").description("유저 전화번호"),
                            fieldWithPath("deviceKey").description("유저 디바이스 키")
                        )
                        .responseFields(
                            fieldWithPath("accessToken").description("액세스 토큰"),
                            fieldWithPath("accessTokenExpiresIn").description("액세스 토큰 만료시간"),
                            fieldWithPath("userUuid").description("유저 uuid")
                        )
                        .responseSchema(Schema.schema("TokenDto"))
                        .requestSchema(Schema.schema("UserLoginRequest"))
                        .build()
                ))
        );

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @DisplayName("유저 SNS 로그인 docs")
    void snsUserLogin() throws Exception {

        //give
        UserSNSLoginRequest request = UserSNSLoginRequestFixture.make();
        String requestBody = ControllerTestConfig.objectMapper.writeValueAsString(request);

        when(userLoginFacade.snsLogin(any()))
            .thenReturn(TokenDtoFixture.createTokenDto());

        //then
        ResultActions resultActions = ControllerTestConfig.mockMvc.perform(
            RestDocumentationRequestBuilders.post(DEFAULT_URL + "/sns")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestBody)
        ).andDo(
            document("유저 SNS 로그인 docs",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(
                    ResourceSnippetParameters.builder()
                        .tag("유저 - 로그인")
                        .description("유저 SNS 로그인")
                        .requestFields(
                            fieldWithPath("email").description("유저 email"),
                            fieldWithPath("snsType").description(
                                "유저 snsType [KAKAO, NAVER, GOOGLE]"),
                            fieldWithPath("snsUniqueId").description("유저 sns 고유 Id 일련번호"),
                            fieldWithPath("deviceKey").description("유저 디바이스 키")
                        )
                        .responseFields(
                            fieldWithPath("accessToken").description("액세스 토큰"),
                            fieldWithPath("accessTokenExpiresIn").description("액세스 토큰 만료시간"),
                            fieldWithPath("userUuid").description("유저 uuid")
                        )
                        .requestSchema(Schema.schema("UserSNSLoginRequest"))
                        .responseSchema(Schema.schema("TokenDto"))
                        .build()
                ))
        );

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @DisplayName("유저 토큰 재발급 docs")
    void refreshTokenDocs() throws Exception {

        //give
        when(userLoginFacade.refresh(any()))
            .thenReturn(TokenDtoFixture.createTokenDto());

        //then
        ResultActions resultActions = ControllerTestConfig.mockMvc.perform(
            RestDocumentationRequestBuilders.post(DEFAULT_URL + "/refresh")
                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(
            document("유저 access token 재발급 요청",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(
                    ResourceSnippetParameters.builder()
                        .tag("유저 - 로그인")
                        .description("유저 access token 재발급 요청")
                        .responseFields(
                            fieldWithPath("accessToken").description("액세스 토큰"),
                            fieldWithPath("accessTokenExpiresIn").description("액세스 토큰 만료시간"),
                            fieldWithPath("userUuid").description("유저 uuid")
                        )
                        .responseSchema(Schema.schema("TokenDto"))
                        .build()
                ))
        );

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("유저 토큰 재발급(refresh) docs")
    void refreshTokenDocs_fail() throws Exception {

        //give
        when(userLoginFacade.refresh(any())).thenThrow(UserTokenException.refreshExpired());

        //then
        ResultActions resultActions = ControllerTestConfig.mockMvc.perform(
            RestDocumentationRequestBuilders.post(DEFAULT_URL + "/refresh")
                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(
            document("유저 access token 재발급 요청 - refresh token 만료 시",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(
                    ResourceSnippetParameters.builder()
                        .tag("유저 - 로그인")
                        .description("유저 access token 재발급 요청 - refresh token 만료 시")
                        .responseFields(
                            fieldWithPath("timestamp").description("exception 발생 시각"),
                            fieldWithPath("status").description("http status - refresh 만료 (500)"),
                            fieldWithPath("error").description("시스템 에러 메세지"),
                            fieldWithPath("message").description("사용자 에러 메세지"),
                            fieldWithPath("path").description("요청 경로")
                        )
                        .build()
                ))
        );

        resultActions.andExpect(MockMvcResultMatchers.status().is(500));
    }
}