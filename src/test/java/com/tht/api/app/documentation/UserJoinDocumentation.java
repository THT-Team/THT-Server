package com.tht.api.app.documentation;

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
import com.tht.api.app.controller.UserJoinController;
import com.tht.api.app.facade.user.UserJoinFacade;
import com.tht.api.app.facade.user.request.UserSignUpRequest;
import com.tht.api.app.facade.user.request.UserSnsSignUpRequest;
import com.tht.api.app.facade.user.response.AuthNumberResponse;
import com.tht.api.app.facade.user.response.UserNickNameValidResponse;
import com.tht.api.app.facade.user.response.UserSignUpResponse;
import com.tht.api.app.unit.controller.config.ControllerTestConfig;
import com.tht.api.app.unit.fixture.user.UserSignUpInfoResponseFixture;
import com.tht.api.app.unit.fixture.user.UserSignUpRequestFixture;
import com.tht.api.app.unit.fixture.user.UserSnsSignUpRequestFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(UserJoinController.class)
class UserJoinDocumentation extends ControllerTestConfig {

    private static final String DEFAULT_URL = "/users/join";
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
                        .tag("유저 - 회원가입")
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
    @DisplayName("유저 닉네인 중복체크 - api docs ")
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
                        .tag("유저 - 회원가입")
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
    @DisplayName("유저 일반 회원가입 api - docs")
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
                        .tag("유저 - 회원가입")
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

                            fieldWithPath("photoList").type(JsonFieldType.ARRAY)
                                .description("유저 사진 url 리스트 - String Array"),
                            fieldWithPath("interestList").description(
                                "유저 관심사 idx 리스트 - String Array"),
                            fieldWithPath("idealTypeList").description(
                                "유저 이상형 idx 리스트 - String Array"),

                            fieldWithPath("snsType").type(JsonFieldType.STRING)
                                .description("회원가입 타입 - [NORMAL, KAKAO, NAVER, GOOGLE]"),
                            fieldWithPath("snsUniqueId").description("sns 고유 id 값")
                        )
                        .responseFields(
                            fieldWithPath("accessToken").description("액세스 토큰"),
                            fieldWithPath("accessTokenExpiresIn").description("액세스 토큰 만료시간")
                        )
                        .responseSchema(Schema.schema("UserSignUpResponse"))
                        .requestSchema(Schema.schema("UserSignUpRequest"))
                        .build()
                ))
        );

        resultActions.andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    @DisplayName("유저 회원가입 정보 조회 api test - docs")
    void getUserSignUpInfo() throws Exception {

        //given
        String nickName = "test 닉네임";
        when(userJoinFacade.getUserSignUpInfo(anyString()))
            .thenReturn(UserSignUpInfoResponseFixture.make());

        //then
        ResultActions resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders.get(
                    DEFAULT_URL + "/exist/user-info/{phone-number}",
                    nickName)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(
            document("유저 회원가입 정보 조회",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(
                    ResourceSnippetParameters.builder()
                        .tag("유저 - 회원가입")
                        .description("유저 회원가입 이력 조회")
                        .pathParameters(parameterWithName("phone-number").description("핸드폰 번호"))
                        .requestFields()
                        .responseFields(
                            fieldWithPath("isSignUp").description("가입 여부"),
                            fieldWithPath("typeList").description("가입한 계정 타입 [NORMAL, KAKAO, NAVER, GOOGLE]")
                        )
                        .responseSchema(Schema.schema("UserSignUpInfoResponse"))
                        .build()
                )
            ));

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("SNS 유저 아이디 통합 가입 api test - docs")
    void integratedUserJoin() throws Exception {

        //give
        UserSnsSignUpRequest make = UserSnsSignUpRequestFixture.makeSNSType();
        String requestBody = objectMapper.writeValueAsString(make);

        when(userJoinFacade.integratedSnsId(any())).thenReturn(new UserSignUpResponse("token", 1L));

        //then
        ResultActions resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders.post(DEFAULT_URL + "/signup/sns")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestBody)
        ).andDo(
            document("유저 SNS 아이디 통합 회원 가입",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(
                    ResourceSnippetParameters.builder()
                        .tag("유저 - 회원가입")
                        .description("유저 SNS 아이디 통합 회원 가입")
                        .requestFields(
                            fieldWithPath("phoneNumber").description("전화번호"),
                            fieldWithPath("deviceKey").description("유저 디바이스 키"),

                            fieldWithPath("snsType").type(JsonFieldType.STRING)
                                .description("회원가입 타입 - KAKAO, NAVER, GOOGLE"),
                            fieldWithPath("snsUniqueId").description("sns 고유 id 값"),
                            fieldWithPath("email").description("sns email")
                        )
                        .responseFields(
                            fieldWithPath("accessToken").description("액세스 토큰"),
                            fieldWithPath("accessTokenExpiresIn").description("액세스 토큰 만료시간")
                        )
                        .responseSchema(Schema.schema("UserSignUpResponse"))
                        .requestSchema(Schema.schema("UserSignUpResponse"))
                        .build()
                ))
        );

        resultActions.andExpect(MockMvcResultMatchers.status().isCreated());

    }

}
