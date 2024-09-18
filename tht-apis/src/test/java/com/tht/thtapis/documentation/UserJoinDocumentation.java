package com.tht.thtapis.documentation;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.tht.enums.EnumDocsUtils;
import com.tht.enums.agreement.AgreementCategory;
import com.tht.enums.user.UserFrequency;
import com.tht.enums.user.UserReligion;
import com.tht.thtapis.controller.config.ControllerTestConfig;
import com.tht.thtapis.facade.user.AgreementFacade;
import com.tht.thtapis.facade.user.UserJoinFacade;
import com.tht.thtapis.facade.user.request.UserSignUpRequest;
import com.tht.thtapis.facade.user.request.UserSnsSignUpRequest;
import com.tht.thtapis.facade.user.response.AuthNumberResponse;
import com.tht.thtapis.facade.user.response.UserNickNameValidResponse;
import com.tht.thtapis.fixture.TokenDtoFixture;
import com.tht.thtapis.fixture.user.AgreementMainCategoryResponseFixture;
import com.tht.thtapis.fixture.user.UserSignUpInfoResponseFixture;
import com.tht.thtapis.fixture.user.UserSignUpRequestFixture;
import com.tht.thtapis.fixture.user.UserSnsSignUpRequestFixture;
import com.tht.thtapis.ui.UserJoinController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

@WebMvcTest(UserJoinController.class)
class UserJoinDocumentation extends ControllerTestConfig {

    private static final String DEFAULT_URL = "/users/join";

    @MockBean
    UserJoinFacade userJoinFacade;

    @MockBean
    AgreementFacade agreementFacade;

    @Test
    @DisplayName("유저 번호 인증 발급 docs")
    void getCertificationNumber() throws Exception {

        //given
        String phoneNumber = "01012345678";
        when(userJoinFacade.issueAuthenticationNumber(anyString())).thenReturn(
            new AuthNumberResponse("01012345678", 314215));

        //then
        ResultActions resultActions = ControllerTestConfig.mockMvc.perform(
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
                        .tag("회원가입")
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
    @DisplayName("유저 닉네인 중복체크 docs ")
    void duplicateNickname() throws Exception {

        //given
        String nickName = "test 닉네임";
        when(userJoinFacade.checkDuplicateNickName(anyString()))
            .thenReturn(new UserNickNameValidResponse(true));

        //then
        ResultActions resultActions = ControllerTestConfig.mockMvc.perform(
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
                        .tag("회원가입")
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
    @DisplayName("유저 일반 회원가입 docs")
    void normalUserJoin() throws Exception {

        //give
        UserSignUpRequest make = UserSignUpRequestFixture.make();
        String requestBody = ControllerTestConfig.objectMapper.writeValueAsString(make);

        when(userJoinFacade.signUp(any())).thenReturn(TokenDtoFixture.createTokenDto());

        //then
        ResultActions resultActions = ControllerTestConfig.mockMvc.perform(
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
                        .tag("회원가입")
                        .description("유저 일반 회원가입")
                        .requestFields(
                            fieldWithPath("username").description("닉네임"),
                            fieldWithPath("phoneNumber").description("전화번호"),
                            fieldWithPath("email").description("이메일"),
                            fieldWithPath("birthDay").description("생일"),
                            fieldWithPath("introduction").description("자기소개"),
                            fieldWithPath("gender").description("성별"),
                            fieldWithPath("preferGender").description("선호 성별"),

                            fieldWithPath("agreement").description("약관 동의 내역"),
                            fieldWithPath("agreement.serviceUseAgree").description("서비스 약관 동의"),
                            fieldWithPath("agreement.personalPrivacyInfoAgree").description(
                                "개인정보 이용 약관 동의"),
                            fieldWithPath("agreement.marketingAgree").description("마케팅 동의 내역"),
                            fieldWithPath("agreement.locationServiceAgree").description("위치정보 동의 내역"),

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
                            fieldWithPath("snsUniqueId").description("sns 고유 id 값"),
                            fieldWithPath("tall").type(JsonFieldType.NUMBER).description("키 (cm)"),
                            fieldWithPath("smoking").type(JsonFieldType.STRING).description("흡연여부 : " + EnumDocsUtils.getTypesFieldList(UserFrequency.class)),
                            fieldWithPath("drinking").type(JsonFieldType.STRING).description("주량(음주 여부) : " + EnumDocsUtils.getTypesFieldList(UserFrequency.class)),
                            fieldWithPath("religion").type(JsonFieldType.STRING).description("종교 : " + EnumDocsUtils.getTypesFieldList(UserReligion.class))
                        )
                        .responseFields(
                            fieldWithPath("accessToken").description("액세스 토큰"),
                            fieldWithPath("accessTokenExpiresIn").description("액세스 토큰 만료시간"),
                            fieldWithPath("userUuid").description("유저 uuid")
                        )
                        .responseSchema(Schema.schema("TokenDto"))
                        .requestSchema(Schema.schema("UserSignUpRequest"))
                        .build()
                ))
        );

        resultActions.andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    @DisplayName("유저 회원가입 정보 조회 api docs")
    void getUserSignUpInfo() throws Exception {

        //given
        String nickName = "test 닉네임";
        when(userJoinFacade.getUserSignUpInfo(anyString()))
            .thenReturn(UserSignUpInfoResponseFixture.make());

        //then
        ResultActions resultActions = ControllerTestConfig.mockMvc.perform(
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
                        .tag("회원가입")
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
    @DisplayName("SNS 유저 아이디 통합 가입 docs")
    void integratedUserJoin() throws Exception {

        //give
        UserSnsSignUpRequest make = UserSnsSignUpRequestFixture.makeSNSType();
        String requestBody = ControllerTestConfig.objectMapper.writeValueAsString(make);

        when(userJoinFacade.integratedSnsId(any())).thenReturn(TokenDtoFixture.createTokenDto());

        //then
        ResultActions resultActions = ControllerTestConfig.mockMvc.perform(
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
                        .tag("회원가입")
                        .description("유저 SNS 아이디 통합 회원 가입")
                        .requestFields(
                            fieldWithPath("phoneNumber").description("전화번호"),

                            fieldWithPath("snsType").type(JsonFieldType.STRING)
                                .description("회원가입 타입 - KAKAO, NAVER, GOOGLE"),
                            fieldWithPath("snsUniqueId").description("sns 고유 id 값"),
                            fieldWithPath("email").description("sns email")
                        )
                        .responseFields(
                            fieldWithPath("accessToken").description("액세스 토큰"),
                            fieldWithPath("accessTokenExpiresIn").description("액세스 토큰 만료시간"),
                            fieldWithPath("userUuid").description("유저 uuid")
                        )
                        .responseSchema(Schema.schema("TokenDto"))
                        .requestSchema(Schema.schema("UserSnsSignUpRequest"))
                        .build()
                ))
        );

        resultActions.andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    @DisplayName("약관동의 데이터 리스트 조회 api docs")
    void getAgreementListDocs() throws Exception {

        //give
        when(agreementFacade.getMainCategoryList()).thenReturn(List.of(AgreementMainCategoryResponseFixture.make()));

        //then
        ResultActions resultActions = ControllerTestConfig.mockMvc.perform(
                RestDocumentationRequestBuilders.get(DEFAULT_URL + "/agreements/main-category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                document("회원가입 약관동의 대분류 카테고리 조회",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(
                                ResourceSnippetParameters.builder()
                                        .tag("회원가입")
                                        .description("유저 회원가입 시 약관동의 대분류 카테고리 리스트 조회")
                                        .requestFields()
                                        .responseFields(
                                                fieldWithPath("[].name").type(JsonFieldType.STRING).description(String.format("약관 카테고리 변수명 - %s", EnumDocsUtils.getTypesFieldList(AgreementCategory.class))),
                                                fieldWithPath("[].subject").type(JsonFieldType.STRING).description("약관 카테고리 명칭"),
                                                fieldWithPath("[].isRequired").type(JsonFieldType.BOOLEAN).description("필수 여부"),
                                                fieldWithPath("[].description").type(JsonFieldType.STRING).description("설명"),
                                                fieldWithPath("[].detailLink").type(JsonFieldType.STRING).description("본문 상세 문서 링크")
                                        )
                                        .responseSchema(Schema.schema(""))
                                        .build()
                        )
                )
        );

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());

    }
}
