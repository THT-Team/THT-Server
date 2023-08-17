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

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.tht.api.app.controller.UserController;
import com.tht.api.app.facade.user.UserFacade;
import com.tht.api.app.unit.controller.config.ControllerTestConfig;
import com.tht.api.app.unit.controller.config.WithCustomMockUser;
import com.tht.api.app.unit.fixture.main.MainScreenResponseFixture;
import com.tht.api.app.unit.fixture.main.MainScreenUserInfoRequestFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(UserController.class)
public class MainDocumentation extends ControllerTestConfig {

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
}
