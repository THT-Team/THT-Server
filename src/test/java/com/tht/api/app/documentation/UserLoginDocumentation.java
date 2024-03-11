package com.tht.api.app.documentation;

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
import com.tht.api.app.controller.UserLoginController;
import com.tht.api.app.controller.config.ControllerTestConfig;
import com.tht.api.app.facade.user.UserLoginFacade;
import com.tht.api.app.facade.user.request.UserLoginRequest;
import com.tht.api.app.facade.user.request.UserSNSLoginRequest;
import com.tht.api.app.facade.user.response.UserLoginResponse;
import com.tht.api.app.fixture.user.UserLoginRequestFixture;
import com.tht.api.app.fixture.user.UserSNSLoginRequestFixture;
import com.tht.api.exception.custom.UserTokenException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(UserLoginController.class)
class UserLoginDocumentation extends ControllerTestConfig {

    private static final String DEFAULT_URL = "/users/login";

    @MockBean
    UserLoginFacade userLoginFacade;

    @Test
    @DisplayName("유저 일반 로그인  api test - docs (성공)")
    void normalUserLogin() throws Exception {

        //give
        UserLoginRequest request = UserLoginRequestFixture.make();
        String requestBody = objectMapper.writeValueAsString(request);

        when(userLoginFacade.login(any())).thenReturn(new UserLoginResponse("access_token", 1701306000));

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
                        .tag("유저 - 로그인")
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

    @Test
    @DisplayName("유저 SNS 로그인  api test - docs")
    void snsUserLogin() throws Exception {

        //give
        UserSNSLoginRequest request = UserSNSLoginRequestFixture.make();
        String requestBody = objectMapper.writeValueAsString(request);

        when(userLoginFacade.snsLogin(any())).thenReturn(new UserLoginResponse("access_token", 1701306000));

        //then
        ResultActions resultActions = mockMvc.perform(
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
                            fieldWithPath("accessTokenExpiresIn").description("액세스 토큰 만료시간")
                        )
                        .requestSchema(Schema.schema("UserSNSLoginRequest"))
                        .responseSchema(Schema.schema("UserLoginResponse"))
                        .build()
                ))
        );

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @DisplayName("유저 토큰 재발급 api docs")
    void refreshTokenDocs() throws Exception {

        //give
        when(userLoginFacade.refresh(any())).thenReturn(new UserLoginResponse("access_token", 1701306000));

        //then
        ResultActions resultActions = mockMvc.perform(
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
                            fieldWithPath("accessTokenExpiresIn").description("액세스 토큰 만료시간")
                        )
                        .responseSchema(Schema.schema("UserLoginResponse"))
                        .build()
                ))
        );

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("유저 토큰 재발급 - refresh token 만료 시 api docs")
    void refreshTokenDocs_fail() throws Exception {

        //give
        when(userLoginFacade.refresh(any())).thenThrow(UserTokenException.refreshExpired());

        //then
        ResultActions resultActions = mockMvc.perform(
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