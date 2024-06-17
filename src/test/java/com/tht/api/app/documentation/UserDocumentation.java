package com.tht.api.app.documentation;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.tht.api.app.controller.UserController;
import com.tht.api.app.controller.config.ControllerTestConfig;
import com.tht.api.app.controller.config.WithCustomMockUser;
import com.tht.api.app.controller.steps.UserControllerSteps;
import com.tht.api.app.entity.enums.Gender;
import com.tht.api.app.entity.enums.UserFrequency;
import com.tht.api.app.entity.enums.UserReligion;
import com.tht.api.app.facade.user.UserFacade;
import com.tht.api.app.facade.user.request.*;
import com.tht.api.app.facade.user.response.UserLoginResponse;
import com.tht.api.app.fixture.user.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

@WebMvcTest(UserController.class)
class UserDocumentation extends ControllerTestConfig {

    @MockBean
    UserFacade userFacade;

    @Test
    @WithCustomMockUser
    @DisplayName("유저 신고하기 api - docs")
    void reportUserDocs() throws Exception {
        //give
        String requestBody = objectMapper.writeValueAsString(UserRequestFixture.make());

        //then
        ResultActions resultActions = mockMvc.perform(
                post("/user/report")
                        .header("Authorization", "Bearer {ACCESS_TOKEN}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(requestBody)
        ).andDo(
                document("유저 신고하기 docs",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(
                                ResourceSnippetParameters.builder()
                                        .tag("유저")
                                        .description("유저 신고하기")
                                        .requestFields(
                                                fieldWithPath("reportUserUuid").description("신고할 유저 uuid"),
                                                fieldWithPath("reason").description("신고 사유")
                                        )
                                        .responseFields()
                                        .requestSchema(Schema.schema("UserReportRequest"))
                                        .build()
                        ))
        );

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @WithCustomMockUser
    @DisplayName("유저 차단하기 api - docs")
    void blockUserDocs() throws Exception {

        //give
        String userUuid = "차단할-user-uuid";

        //then
        ResultActions resultActions = mockMvc.perform(
                post("/user/block/{block-user-uuid}", userUuid)
                        .header("Authorization", "Bearer {ACCESS_TOKEN}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                document("유저 차단하기 docs",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(
                                ResourceSnippetParameters.builder()
                                        .tag("유저")
                                        .description("유저 차단하기")
                                        .pathParameters(
                                                parameterWithName("block-user-uuid").description("차단할 유저 uuid")
                                        )
                                        .requestFields()
                                        .responseFields()
                                        .requestSchema(Schema.schema("UserReportRequest"))
                                        .build()
                        ))
        );

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @WithCustomMockUser
    @DisplayName("유저 정보 상세조회 api - docs")
    void getUserDetails() throws Exception {

        //given
        when(userFacade.getUserDetail(anyString()))
                .thenReturn(UserDetailResponseFixture.make()
        );

        //then
        ResultActions resultActions = mockMvc.perform(
                get("/user")
                        .header("Authorization", "Bearer {ACCESS_TOKEN}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                document("유저 정보 조회 docs",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(
                                ResourceSnippetParameters.builder()
                                        .tag("마이페이지")
                                        .description("마이페이지 유저 정보 상세 조회")
                                        .requestFields()
                                        .responseFields(
                                                fieldWithPath("username").description("유저 이름"),
                                                fieldWithPath("userUuid").description("유저 고유 번호"),
                                                fieldWithPath("age").description("나이"),
                                                fieldWithPath("address").description("주소"),
                                                fieldWithPath("introduction").description("자기소개"),
                                                fieldWithPath("phoneNumber").description("전화번호"),
                                                fieldWithPath("email").description("이메일"),
                                                fieldWithPath("birthDay").description("생년월일"),

                                                fieldWithPath("gender").description(String.format("성별 - %s", EnumDocsUtils.getTypesFieldList(Gender.class))),
                                                fieldWithPath("prefer_gender").description(String.format("선호 성별 - %s", EnumDocsUtils.getTypesFieldList(Gender.class))),
                                                fieldWithPath("tall").description("키"),
                                                fieldWithPath("smoking").description(String.format("흡연 여부 - %s", EnumDocsUtils.getTypesFieldList(UserFrequency.class))),
                                                fieldWithPath("drinking").description(String.format("술 - %s", EnumDocsUtils.getTypesFieldList(UserFrequency.class))),
                                                fieldWithPath("religion").description(String.format("종교 - %s", EnumDocsUtils.getTypesFieldList(UserReligion.class))),

                                                fieldWithPath("idealTypeList").description("선택한 이상형 리스트"),
                                                fieldWithPath("idealTypeList[].idx").description("이상형 idx"),
                                                fieldWithPath("idealTypeList[].name").description("이상형 명칭"),
                                                fieldWithPath("idealTypeList[].emojiCode").description("이상형 코드"),

                                                fieldWithPath("interestsList").description("선택한 관심사 리스트"),
                                                fieldWithPath("interestsList[].idx").description("관심사 idx"),
                                                fieldWithPath("interestsList[].name").description("관심사 명칭"),
                                                fieldWithPath("interestsList[].emojiCode").description("관심사 코드"),

                                                fieldWithPath("userProfilePhotos").description("유저 프로필 이미지 리스트"),
                                                fieldWithPath("userProfilePhotos[].url").description("이미지 url"),
                                                fieldWithPath("userProfilePhotos[].priority")
                                                        .description("사진 우선순위 (1:프로필)"),

                                                fieldWithPath("userAgreements").type(JsonFieldType.OBJECT).description("유저 약관동의 내역"),
                                                fieldWithPath("userAgreements.serviceUseAgree").type(JsonFieldType.BOOLEAN).description("서비스 약관 동의 내역"),
                                                fieldWithPath("userAgreements.personalPrivacyInfoAgree").type(JsonFieldType.BOOLEAN).description("개인정보 이용 약관 동의 내역"),
                                                fieldWithPath("userAgreements.marketingAgree").type(JsonFieldType.BOOLEAN).description("마케팅 동의 내역"),
                                                fieldWithPath("userAgreements.locationServiceAgree").type(JsonFieldType.BOOLEAN).description("위치정보 동의 내역")

                                                )
                                        .responseSchema(Schema.schema("UserDetailResponse"))
                                        .build()
                        ))
        );

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @WithCustomMockUser
    @DisplayName("다른 유저 정보 상세조회 api - docs")
    void getAnotherUserDetails() throws Exception {

        //given
        when(userFacade.getUserDetail(anyString())).thenReturn(
                UserDetailResponseFixture.make()
        );

        //then
        ResultActions resultActions = mockMvc.perform(
                get("/user/another/{user-uuid}", "user-uuid")
                        .header("Authorization", "Bearer {ACCESS_TOKEN}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                document("특정 유저 정보 조회 docs",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(
                                ResourceSnippetParameters.builder()
                                        .tag("좋아요")
                                        .description("특정 유저 정보 상세 조회")
                                        .pathParameters(
                                                parameterWithName("user-uuid").description("조회하고자 하는 유저의 고유 번호")
                                        )
                                        .requestFields()
                                        .responseFields(
                                                fieldWithPath("username").description("유저 이름"),
                                                fieldWithPath("userUuid").description("유저 고유 번호"),
                                                fieldWithPath("age").description("나이"),
                                                fieldWithPath("address").description("주소"),
                                                fieldWithPath("introduction").description("자기소개"),
                                                fieldWithPath("phoneNumber").description("전화번호"),
                                                fieldWithPath("email").description("이메일"),
                                                fieldWithPath("birthDay").description("생년월일"),

                                                fieldWithPath("gender").description(String.format("성별 - %s", EnumDocsUtils.getTypesFieldList(Gender.class))),
                                                fieldWithPath("prefer_gender").description(String.format("선호 성별 - %s", EnumDocsUtils.getTypesFieldList(Gender.class))),
                                                fieldWithPath("tall").description("키"),
                                                fieldWithPath("smoking").description(String.format("흡연 여부 - %s", EnumDocsUtils.getTypesFieldList(UserFrequency.class))),
                                                fieldWithPath("drinking").description(String.format("술 - %s", EnumDocsUtils.getTypesFieldList(UserFrequency.class))),
                                                fieldWithPath("religion").description(String.format("종교 - %s", EnumDocsUtils.getTypesFieldList(UserReligion.class))),

                                                fieldWithPath("idealTypeList").description("선택한 이상형 리스트"),
                                                fieldWithPath("idealTypeList[].idx").description("이상형 idx"),
                                                fieldWithPath("idealTypeList[].name").description("이상형 명칭"),
                                                fieldWithPath("idealTypeList[].emojiCode").description("이상형 코드"),

                                                fieldWithPath("interestsList").description("선택한 관심사 리스트"),
                                                fieldWithPath("interestsList[].idx").description("관심사 idx"),
                                                fieldWithPath("interestsList[].name").description("관심사 명칭"),
                                                fieldWithPath("interestsList[].emojiCode").description("관심사 코드"),

                                                fieldWithPath("userProfilePhotos").description("유저 프로필 이미지 리스트"),
                                                fieldWithPath("userProfilePhotos[].url").description("이미지 url"),
                                                fieldWithPath("userProfilePhotos[].priority")
                                                        .description("사진 우선순위 (1:프로필)"),

                                                fieldWithPath("userAgreements").type(JsonFieldType.OBJECT).description("유저 약관동의 내역"),
                                                fieldWithPath("userAgreements.serviceUseAgree").type(JsonFieldType.BOOLEAN).description("서비스 약관 동의 내역"),
                                                fieldWithPath("userAgreements.personalPrivacyInfoAgree").type(JsonFieldType.BOOLEAN).description("개인정보 이용 약관 동의 내역"),
                                                fieldWithPath("userAgreements.marketingAgree").type(JsonFieldType.BOOLEAN).description("마케팅 동의 내역"),
                                                fieldWithPath("userAgreements.locationServiceAgree").type(JsonFieldType.BOOLEAN).description("위치정보 동의 내역")

                                        )
                                        .responseSchema(Schema.schema("UserDetailResponse"))
                                        .build()
                        ))
        );

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @DisplayName("유저 핸드폰 번호 업데이트 api docs")
    void updatePhoneNumber() throws Exception {

        //then
        String phoneNumber = "01032107781";

        ResultActions resultActions = mockMvc.perform(
                patch("/user/phone-number/{phone-number}", phoneNumber)
                        .header("Authorization", "Bearer {ACCESS_TOKEN}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                document("유저 핸드폰 번호 수정 api docs",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(
                                ResourceSnippetParameters.builder()
                                        .tag("마이페이지")
                                        .description("마이페이지 유저 핸드폰 번호 수정")
                                        .pathParameters(
                                                parameterWithName("phone-number").description("수정할 핸드폰번호 [숫자만 9~11자리]")
                                        )
                                        .requestFields()
                                        .responseFields()
                                        .build()
                        ))
        );

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @DisplayName("유저 이메일 업데이트 api docs ")
    void updateEmail() throws Exception {

        //then
        String email = "happy@naver.com";

        ResultActions resultActions = mockMvc.perform(
                patch("/user/email/{email}", email)
                        .header("Authorization", "Bearer {ACCESS_TOKEN}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                document("유저 이메일 수정 api docs",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(
                                ResourceSnippetParameters.builder()
                                        .tag("마이페이지")
                                        .description("마이페이지 유저 이메일 수정")
                                        .pathParameters(
                                                parameterWithName("email").description("수정할 이메일")
                                        )
                                        .requestFields()
                                        .responseFields()
                                        .build()
                        ))
        );

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());

    }

    @WithCustomMockUser
    @Test
    @DisplayName("관심사 수정 api docs")
    void updateInterests() throws Exception {

        ModifiedInterestsRequest request = ModifiedInterestsRequestFixture.make();

        //then
        ResultActions resultActions = UserControllerSteps.유저_관심사_수정_요청(request)
                .andDo(
                        MockMvcRestDocumentationWrapper.document("유저 관심사 수정 api docs",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("마이페이지")
                                                .description("유저가 선택한 관심사 목록 수정")
                                                .requestFields(
                                                        fieldWithPath("interestList").description("이상형 idx List")
                                                )
                                                .responseFields()
                                                .requestSchema(Schema.schema("ModifiedInterestsRequest"))
                                                .build()
                                )
                        )
                );

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @WithCustomMockUser
    @Test
    @DisplayName("이상형타입 수정 api docs")
    void updateIdealType() throws Exception {

        ModifiedIdealTypeRequest request = ModifiedIdealTypeRequestFixture.make();

        //then
        ResultActions resultActions = UserControllerSteps.유저_이상형타입_수정_요청(request)
                .andDo(
                        MockMvcRestDocumentationWrapper.document("유저 이상형타입 수정 api docs",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("마이페이지")
                                                .description("유저가 선택한 이상형타입 목록 수정")
                                                .requestFields(
                                                        fieldWithPath("idealTypeList").description("이상형 idx List")
                                                )
                                                .responseFields()
                                                .requestSchema(Schema.schema("ModifiedIdealTypeRequest"))
                                                .build()
                                )
                        )
                );

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @WithCustomMockUser
    @Test
    @DisplayName("내 위치 업데이트 api docs")
    void updateLocation() throws Exception {

        //given
        UserLocationRequest request = UserLocationRequestFixture.make();

        String requestBody = objectMapper.writeValueAsString(request);

        var resultActions = mockMvc.perform(
                        patch("/user/location")
                                .header("Authorization", "Bearer {ACCESS_TOKEN")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(requestBody))
                .andDo(
                        document("유저 내 위치 수정 api docs",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("마이페이지")
                                                .description("유저의 위치 주소를 업데이트")
                                                .requestFields(
                                                        fieldWithPath("address").description("주소"),
                                                        fieldWithPath("regionCode").description("자치구 코드"),
                                                        fieldWithPath("lat").description("위도 좌표"),
                                                        fieldWithPath("lon").description("경도 좌표")
                                                )
                                                .responseFields()
                                                .build()
                                )
                        )
                );

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("유저 이름 업데이트 api docs")
    void updateName() throws Exception {

        //given
        String name = "수정할 유저 이름";
        UserLoginResponse response = new UserLoginResponse(
                "access-token",
                14123980
        );

        when(userFacade.updateNickName(any(), anyString())).thenReturn(response);

        //then
        ResultActions resultActions = mockMvc.perform(
                patch("/user/name/{nick-name}", name)
                        .header("Authorization", "Bearer {ACCESS_TOKEN}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                document("유저 이름(닉네임) 수정 api docs",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(
                                ResourceSnippetParameters.builder()
                                        .tag("마이페이지")
                                        .description("마이페이지 유저 이름(닉네임) 수정")
                                        .pathParameters(
                                                parameterWithName("nick-name").description("수정할 유저 이름")
                                        )
                                        .requestFields()
                                        .responseFields(
                                                fieldWithPath("accessToken").description("액세스 토큰"),
                                                fieldWithPath("accessTokenExpiresIn").description(
                                                        "액세스 토큰 만료시간 (unix time stamp)")
                                        )
                                        .responseSchema(Schema.schema("ModifiedIdealTypeRequest"))
                                        .build()

                        ))
        );

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());

    }

    @WithCustomMockUser
    @Test
    @DisplayName("자기소개 업데이트 api docs")
    void updateIntroduction() throws Exception {

        //given
        String introduction = "업데이트할 자기소개 내용";
        Map<String, String> request = new HashMap<>();
        request.put("introduction", introduction);

        String requestBody = objectMapper.writeValueAsString(request);

        var resultActions = mockMvc.perform(
                        patch("/user/introduction")
                                .header("Authorization", "Bearer {ACCESS_TOKEN")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(requestBody))
                .andDo(
                        document("유저 자기소개 업데이트 api docs",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("마이페이지")
                                                .description("유저의 자기소개 내용을 업데이트")
                                                .requestFields(
                                                        fieldWithPath("introduction").description("수정할 자기소개 내용")
                                                )
                                                .responseFields()
                                                .build()
                                )
                        )
                );

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }


    @WithCustomMockUser
    @Test
    @DisplayName("유저 프로필 사진 업데이트 api docs")
    void updateProfile() throws Exception {

        //given
        UserModifyProfilePhotoRequest request = new UserModifyProfilePhotoRequest(
                List.of(
                        new UserProfilePhotoRequest("사진 url", 1),
                        new UserProfilePhotoRequest("사진 url", 2),
                        new UserProfilePhotoRequest("사진 url", 3)
                )
        );

        String requestBody = objectMapper.writeValueAsString(request);

        var resultActions = mockMvc.perform(
                        patch("/user/profile-photo")
                                .header("Authorization", "Bearer {ACCESS_TOKEN")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(requestBody))
                .andDo(
                        document("유저 프로필 사진 업데이트 api docs",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("마이페이지")
                                                .description("유저의 프로필 사진을 업데이트")
                                                .requestFields(
                                                        fieldWithPath("userProfilePhotoList").description(
                                                                "업데이트할 프로필 사진 리스트"),
                                                        fieldWithPath("userProfilePhotoList[0].url").description(
                                                                "업데이트할 프로필 사진 url"),
                                                        fieldWithPath("userProfilePhotoList[0].priority").description(
                                                                "업데이트할 프로필 사진 우선순위")
                                                )
                                                .responseFields()
                                                .build()
                                )
                        )
                );

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @WithCustomMockUser
    @Test
    @DisplayName("유저 선호성별 업데이트 api docs")
    void updatePreferGender() throws Exception {

        //given
        var resultActions = mockMvc.perform(
                        patch("/user/preferred-gender/{gender}", Gender.BISEXUAL.name())
                                .header("Authorization", "Bearer {ACCESS_TOKEN")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andDo(
                        document("유저 선호성별 업데이트 api docs",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("마이페이지")
                                                .description("유저의 선호성별을 업데이트")
                                                .pathParameters(
                                                        parameterWithName("gender").description(
                                                                "선호 성별 [MALE, FEMALE, BISEXUAL]")
                                                )
                                                .requestFields()
                                                .responseFields()
                                                .build()
                                )
                        )
                );

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @WithCustomMockUser
    @Test
    @DisplayName("유저 알림 설정 업데이트 api docs")
    void updateUserAlarm() throws Exception {

        UserAlarmAgreementModifyRequest request = new UserAlarmAgreementModifyRequest(true, true,
                true, true, true);

        String requestBody = objectMapper.writeValueAsString(request);

        //given
        var resultActions = mockMvc.perform(
                        patch("/user/alarm-agreement")
                                .header("Authorization", "Bearer {ACCESS_TOKEN")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(requestBody)
                )
                .andDo(
                        document("유저 알림 설정 업데이트 api docs",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("마이페이지")
                                                .description("유저의 알림 설정 업데이트")
                                                .requestFields(
                                                        fieldWithPath("marketingAlarm").description("마케팅 정보 수신 동의 여부"),
                                                        fieldWithPath("newMatchSuccessAlarm").description(
                                                                "새로운 매치시 알림 동의 여부"),
                                                        fieldWithPath("likeMeAlarm").description("나를 좋아요 알림 동의 여부"),
                                                        fieldWithPath("newConversationAlarm").description(
                                                                "새로운 대화 알림 동의 여부"),
                                                        fieldWithPath("talkAlarm").description("기존 대화 알림 동의 여부")
                                                )
                                                .responseFields()
                                                .requestSchema(Schema.schema("UserAlarmAgreementModifyRequest"))
                                                .build()
                                )
                        )
                );

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @DisplayName("계정탈퇴 api docs")
    @WithCustomMockUser
    @Test
    void deleteUserDocs() throws Exception {

        UserWithDrawRequest request = new UserWithDrawRequest("탈퇴사유", "피드백");

        String requestBody = objectMapper.writeValueAsString(request);

        var result = mockMvc.perform(
                        delete("/user/account-withdrawal")
                                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(requestBody)
                )
                .andDo(
                        document("유저 계정 탈퇴 api docs",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("마이페이지")
                                                .description("유저 계정 탈퇴 요청")
                                                .requestFields(
                                                        fieldWithPath("reason").description("탈퇴 사유"),
                                                        fieldWithPath("feedBack").description("피드백")
                                                )
                                                .responseFields()
                                                .requestSchema(Schema.schema("UserWithDrawRequest"))
                                                .build()
                                )
                        )
                );

        result.andExpect(MockMvcResultMatchers.status().isNoContent());

    }

    @DisplayName("유저 연락처 수 조회 api docs")
    @Test
    @WithCustomMockUser
    void getFriendList() throws Exception {

        when(userFacade.getFriendCount(anyString())).thenReturn(290);

        mockMvc.perform(
                        get("/user/friend-contact-list")
                                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                ).andDo(
                        document("유저 연락처 차단 목록 조회 api docs",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("마이페이지")
                                                .description("유저 연락처 차단 목록 조회")
                                                .requestFields()
                                                .responseFields(
                                                        fieldWithPath("count").description("차단한 연락처 개수")
                                                )
                                                .responseSchema(Schema.schema("UserFriendContactResponse"))
                                                .build()
                                )
                        )
                )
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @DisplayName("유저 연락처 차단 api docs")
    @Test
    @WithCustomMockUser
    void updateFriendList() throws Exception {

        //given
        UserFriendContactRequest request = new UserFriendContactRequest(
                List.of(
                        new ContactDto("친구1", "01044551234"),
                        new ContactDto("친구2", "01041414141"),
                        new ContactDto("친구3", "01042212312"),
                        new ContactDto("친구4", "01044141412"),
                        new ContactDto("친구5", "01012414521")
                )
        );

        String requestBody = objectMapper.writeValueAsString(request);

        when(userFacade.updateFriendContactList(anyString(), anyList())).thenReturn(
                request.contacts().size());

        mockMvc.perform(
                        post("/user/friend-contact-list")
                                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(requestBody)
                ).andDo(
                        document("유저 연락처 차단 api docs",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                resource(
                                        ResourceSnippetParameters.builder()
                                                .tag("마이페이지")
                                                .description("유저 연락처 리스트 차단하기")
                                                .requestFields(
                                                        fieldWithPath("contacts").description("유저 기기에 저장된 차단할 연락처 목록"),
                                                        fieldWithPath("contacts[].name").description("유저가 차단할 연락처 이름"),
                                                        fieldWithPath("contacts[].phoneNumber").description("유저가 차단할 연락처")
                                                )
                                                .responseFields(fieldWithPath("count").description("차단한 연락처 개수")
                                                )
                                                .requestSchema(Schema.schema("UserFriendContactRequest"))
                                                .responseSchema(Schema.schema("UserFriendContactResponse"))
                                                .build()
                                )
                        )
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}