package com.tht.api.app.controller;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.ArgumentMatchers.any;
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
import com.tht.api.app.facade.user.UserJoinFacade;
import com.tht.api.app.facade.user.request.UserSignUpRequest;
import com.tht.api.app.facade.user.response.AuthNumberResponse;
import com.tht.api.app.facade.user.response.UserNickNameValidResponse;
import com.tht.api.app.facade.user.response.UserSignUpResponse;
import com.tht.api.app.fixture.UserSignUpRequestFixture;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(UserJoinController.class)
class UserJoinControllerTest extends ControllerTestConfig {

    private static final String DEFAULT_URL = "/users/join";
    @MockBean
    UserJoinFacade userJoinFacade;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("유저 번호 인증 발급 docs")
    void getCertificationNumber() throws Exception {

        //given
        String phoneNumber = "01012345678";
        when(userJoinFacade.issueAuthenticationNumber(anyString())).thenReturn(
            new AuthNumberResponse("01012345678", 314215));

        //then
        ResultActions resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders.get(
                    DEFAULT_URL + "/certification/phone-number/{phone-number}",
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
    @DisplayName("유저 닉네인 중복체크 api test ")
    void duplicateNickname() throws Exception {

        //given
        String nickName = "test 닉네임";
        when(userJoinFacade.checkDuplicateNickName(anyString()))
            .thenReturn(new UserNickNameValidResponse(true));

        //then
        ResultActions resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders.get(
                    DEFAULT_URL + "/nick-name/duplicate-check/{nick-name}",
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

    @Test
    @DisplayName("유저 일반 회원가입 api test - docs")
    void normalUserJoin() throws Exception {

        //give
        UserSignUpRequest make = UserSignUpRequestFixture.make();
        String requestBody = objectMapper.writeValueAsString(make);

        when(userJoinFacade.signUp(any())).thenReturn(new UserSignUpResponse("token", 1L));

        //then
        ResultActions resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders.post(DEFAULT_URL + "/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestBody)
        ).andDo(
            document("유저 일반 회원가입 docs",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(
                    ResourceSnippetParameters.builder()
                        .tag("유저")
                        .description("유저 일반 회원가입")
                        .requestFields(
                            fieldWithPath("username").description("닉네임"),
                            fieldWithPath("phoneNumber").description("전화번호"),
                            fieldWithPath("email").description("이메일"),
                            fieldWithPath("birthDay").description("생일"),
                            fieldWithPath("introduction").description("자기소개"),
                            fieldWithPath("gender").description("성별"),
                            fieldWithPath("preferGender").description("선호 성별"),
                            fieldWithPath("deviceKey").description("유저 디바이스 키"),
                            fieldWithPath("agreement").description("약관 동의 내역"),
                            fieldWithPath("agreement.serviceUseAgree").description("서비스 약관 동의"),
                            fieldWithPath("agreement.personalPrivacyInfoAgree").description(
                                "개인정보 이용 약관 동의"),
                            fieldWithPath("agreement.locationServiceAgree").description("위치 동의 내역"),
                            fieldWithPath("agreement.marketingAgree").description("마케팅 동의 내역"),
                            fieldWithPath("locationRequest").description("유저 위치 정보"),
                            fieldWithPath("locationRequest.address").description("주소"),
                            fieldWithPath("locationRequest.regionCode").description("자치구 코드"),
                            fieldWithPath("locationRequest.lat").description("위도 좌표"),
                            fieldWithPath("locationRequest.lon").description("경도 좌표"),
                            fieldWithPath("photoList").description("유저 사진 url 리스트"),
                            fieldWithPath("interestList").description("유저 관심사 idx 리스트"),
                            fieldWithPath("idealTypeList").description("유저 이상형 idx 리스트")
                        )
                        .responseFields(
                            fieldWithPath("accessToken").description("액세스 토큰"),
                            fieldWithPath("accessTokenExpiresIn").description("액세스 토큰 만료시간")
                        )
                        .responseSchema(Schema.schema("UserSignUpResponse"))
                        .build()
                ))
        );

        resultActions.andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    @DisplayName("유저 일반 회원가입 api test(실패) - 관심사 최대 사이즈(3개) 초과 테스트")
    void normalUserJoin_interest_fail() throws Exception {

        //give
        UserSignUpRequest make = UserSignUpRequestFixture.ofInterest(List.of(1L,2L,3L,4L));
        String requestBody = objectMapper.writeValueAsString(make);
        when(userJoinFacade.signUp(any())).thenReturn(new UserSignUpResponse("token", 1L));

        //then
        ResultActions resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders.post(DEFAULT_URL + "/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestBody)
        );

        resultActions.andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    @DisplayName("유저 일반 회원가입 api test(실패) - 관심사 최대 사이즈(3개) 초과 테스트")
    void normalUserJoin_idealType_fail() throws Exception {

        //give
        UserSignUpRequest make = UserSignUpRequestFixture.ofIdealType(List.of(1L,2L,3L,4L));
        String requestBody = objectMapper.writeValueAsString(make);
        when(userJoinFacade.signUp(any())).thenReturn(new UserSignUpResponse("token", 1L));

        //then
        ResultActions resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders.post(DEFAULT_URL + "/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestBody)
        );

        resultActions.andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @ParameterizedTest
    @ValueSource(ints = {1,4})
    @DisplayName("유저 일반 회원가입 api test(실패) - 사진은 필수 2장 최대 3장")
    void normalUserJoin_photo_fail(final int size) throws Exception {

        List<String> photo_url = new LinkedList<>();

        for (int i = 0; i < size; i++) {
            photo_url.add(i + "_photo");
        }

        //give
        UserSignUpRequest make = UserSignUpRequestFixture.ofPhoto(photo_url);

        String requestBody = objectMapper.writeValueAsString(make);
        when(userJoinFacade.signUp(any())).thenReturn(new UserSignUpResponse("token", 1L));

        //then
        ResultActions resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders.post(DEFAULT_URL + "/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestBody)
        );

        resultActions.andExpect(MockMvcResultMatchers.status().isBadRequest());

    }
}
