package com.tht.api.app.controller;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.parameterWithName;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.tht.api.app.controller.config.ControllerTestConfig;
import com.tht.api.app.controller.config.WithCustomMockUser;
import com.tht.api.app.facade.user.UserFacade;
import com.tht.api.app.fixture.main.MainScreenResponseFixture;
import com.tht.api.app.fixture.main.MainScreenUserInfoRequestFixture;
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
}