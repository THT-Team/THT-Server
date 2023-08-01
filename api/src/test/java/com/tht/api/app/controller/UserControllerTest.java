package com.tht.api.app.controller;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static com.tht.api.app.controller.steps.UserControllerSteps.유저_관심사_수정_요청;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper;
import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.tht.api.app.controller.config.ControllerTestConfig;
import com.tht.api.app.controller.config.WithCustomMockUser;
import com.tht.api.app.facade.interest.request.ModifiedInterestsRequest;
import com.tht.api.app.facade.user.UserFacade;
import com.tht.api.app.fixture.main.MainScreenResponseFixture;
import com.tht.api.app.fixture.main.MainScreenUserInfoRequestFixture;
import com.tht.api.app.fixture.user.ModifiedInterestsRequestFixture;
import com.tht.api.app.fixture.user.UserDetailResponseFixture;
import com.tht.api.app.fixture.user.UserRequestFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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

    @Test
    @WithCustomMockUser
    @DisplayName("메인화면 유저 리스트 조회 api docs")
    void getMainScreenUserList() throws Exception {

        //give
        String requestBody = objectMapper.writeValueAsString(
            MainScreenUserInfoRequestFixture.make());

        when(userFacade.findAllToDayFallingUserList(anyString(), any()))
            .thenReturn(MainScreenResponseFixture.make());

        //then
        ResultActions resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders.post("/main/daily-falling/users")
                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestBody)
        ).andDo(
            document("메인화면 유저 리스트 조회 docs",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(
                    ResourceSnippetParameters.builder()
                        .tag("메인")
                        .description("메인화면 - 오늘의 주제어를 선택한 유저 리스트 조회")
                        .requestFields(
                            fieldWithPath("alreadySeenUserUuidList").description(
                                "한번 본 유저 uuid 리스트"),
                            fieldWithPath("userDailyFallingCourserIdx").description(
                                "페이징 커서 - 마지막으로 본 UserDailyFalling Idx"),
                            fieldWithPath("size").description("페이징 사이즈")
                        )
                        .responseFields(
                            fieldWithPath("selectDailyFallingIdx").description(
                                "유저가 선택한 그날의 topic idx - 선택하지 않았다면 (-1)"),
                            fieldWithPath("topicExpirationUnixTime").description(
                                "유저 선택한 그날의 topic 만료시간 unix timestamp"),
                            fieldWithPath("userInfos[].username").description("유저 이름"),
                            fieldWithPath("userInfos[].userUuid").description("유저 uuid"),
                            fieldWithPath("userInfos[].age").description("나이"),
                            fieldWithPath("userInfos[].address").description("주소"),
                            fieldWithPath("userInfos[].isBirthDay").description("생일 여부"),
                            fieldWithPath("userInfos[].idealTypeResponseList").description(
                                "선택한 이상형 리스트"),
                            fieldWithPath("userInfos[].idealTypeResponseList[].idx").description(
                                "이상형 idx"),
                            fieldWithPath("userInfos[].idealTypeResponseList[].name").description(
                                "이상형 명칭"),
                            fieldWithPath(
                                "userInfos[].idealTypeResponseList[].emojiCode").description(
                                "이상형 코드"),
                            fieldWithPath("userInfos[].interestResponses").description(
                                "선택한 관심사 리스트"),
                            fieldWithPath("userInfos[].interestResponses[].idx").description(
                                "관심사 idx"),
                            fieldWithPath("userInfos[].interestResponses[].name").description(
                                "관심사 명칭"),
                            fieldWithPath("userInfos[].interestResponses[].emojiCode").description(
                                "관심사 코드"),
                            fieldWithPath("userInfos[].userProfilePhotos").description(
                                "매칭된 유저 프로필 이미지 리스트"),
                            fieldWithPath("userInfos[].userProfilePhotos[].url").description(
                                "이미지 url"),
                            fieldWithPath("userInfos[].userProfilePhotos[].priority").description(
                                "사진 우선순위 (1:프로필)"),
                            fieldWithPath("userInfos[].introduction").description("자기소개"),
                            fieldWithPath("userInfos[].userDailyFallingCourserIdx").description(
                                "페이징 커서 - 그날의 토픽을 선택한 유저 정보 idx")
                        )
                        .requestSchema(Schema.schema("UserLoginRequest"))
                        .responseSchema(Schema.schema("UserLoginResponse"))
                        .build()
                ))
        );

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @WithCustomMockUser
    @DisplayName("유저 신고하기 api - docs")
    void reportUserDocs() throws Exception {
        //give
        String requestBody = objectMapper.writeValueAsString(UserRequestFixture.make());

        //then
        ResultActions resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders.post("/user/report")
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
            RestDocumentationRequestBuilders.post("/user/block/{block-user-uuid}", userUuid)
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
        when(userFacade.getUserDetail(any())).thenReturn(
            UserDetailResponseFixture.make()
        );

        //then
        ResultActions resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders.get("/user")
                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(
            document("유저 정보 조회 docs",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(
                    ResourceSnippetParameters.builder()
                        .tag("유저 - 마이페이지")
                        .description("마이페이지 유저 정보 상세 조회")
                        .requestFields()
                        .responseFields(
                            fieldWithPath("username").description("유저 이름"),
                            fieldWithPath("age").description("나이"),
                            fieldWithPath("address").description("주소"),
                            fieldWithPath("introduction").description("자기소개"),
                            fieldWithPath("phoneNumber").description("전화번호"),
                            fieldWithPath("email").description("이메일"),

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
                                .description("사진 우선순위 (1:프로필)")
                        )
                        .responseSchema(Schema.schema("UserDetailResponse"))
                        .build()
                ))
        );

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @DisplayName("유저 핸드폰 번호 업데이트")
    void updatePhoneNumber() throws Exception {

        //then
        String phoneNumber = "01032107781";

        ResultActions resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders.patch("/user/phone-number/{phone-number}", phoneNumber)
                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(
            document("유저 핸드폰 번호 수정 api docs",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(
                    ResourceSnippetParameters.builder()
                        .tag("유저 - 마이페이지")
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
    @DisplayName("유저 이메일 업데이트 docs 생성")
    void updateEmail() throws Exception {

        //then
        String email = "happy@naver.com";

        ResultActions resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders.patch("/user/email/{email}", email)
                .header("Authorization", "Bearer {ACCESS_TOKEN}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(
            document("유저 이메일 수정 api docs",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(
                    ResourceSnippetParameters.builder()
                        .tag("유저 - 마이페이지")
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
    @DisplayName("관심사 수정 docs")
    void updateInterests() throws Exception {

        ModifiedInterestsRequest request = ModifiedInterestsRequestFixture.make();

        //then
        ResultActions resultActions = 유저_관심사_수정_요청(request)
            .andDo(
                MockMvcRestDocumentationWrapper.document("유저 관심사 수정 api docs",
                    preprocessRequest(prettyPrint()),
                    preprocessResponse(prettyPrint()),
                    resource(
                        ResourceSnippetParameters.builder()
                            .tag("유저 - 마이페이지")
                            .description("유저 선택한 관심사 목록 수정")
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
    @DisplayName("관심사 수정 api 호출 (실패) - 이상형 리스트의 크기는 1~3개 여야한다.")
    void updateInterests_sizeFail() throws Exception {

        ModifiedInterestsRequest request = ModifiedInterestsRequestFixture.ofSize(4);

        var response = 유저_관심사_수정_요청(request);

        response.andExpect(MockMvcResultMatchers.status().isBadRequest());
        response.andExpect(jsonPath("message").value("관심사는 최대 3개를 골라주세요"));
    }

    @WithCustomMockUser
    @Test
    @DisplayName("관심사 수정 api 호출 (실패) - 이상형 리스트가 null 이어서는 안된다.")
    void updateInterests_notNull() throws Exception {

        var response = 유저_관심사_수정_요청(new ModifiedInterestsRequest(null));

        response.andExpect(MockMvcResultMatchers.status().isBadRequest());
        response.andExpect(jsonPath("message").value("interestList 는 null 이어서는 안됩니다."));
    }
}