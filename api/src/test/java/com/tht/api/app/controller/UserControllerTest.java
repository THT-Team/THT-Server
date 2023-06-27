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
import com.tht.api.app.fixture.user.MainScreenUserInfoResponseFixture;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@WebMvcTest(UserController.class)
class UserControllerTest extends ControllerTestConfig {

    @MockBean
    UserFacade userFacade;

    @Test
    @WithCustomMockUser
    @DisplayName("메인화면 유저 리스트 조회 api docs")
    void getMainScreenUserList() throws Exception {

        //give
        MultiValueMap<String, String> paraMap = new LinkedMultiValueMap<>();
        paraMap.set("alreadySeenUserUuidList","1,2,3");
        paraMap.set("userDailyFallingCourserIdx","14104");
        paraMap.set("size", "100");

        when(userFacade.findAllToDayFallingUserList(anyString(), any()))
            .thenReturn(List.of(MainScreenUserInfoResponseFixture.make()));

        //then
        ResultActions resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders.get("/main/daily-falling/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .params(paraMap)
        ).andDo(
            document("메인화면 유저 리스트 조회 docs",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(
                    ResourceSnippetParameters.builder()
                        .tag("메인")
                        .description("메인화면 - 오늘의 주제어를 선택한 유저 리스트 조회")
                        .queryParameters(
                            parameterWithName("alreadySeenUserUuidList").description("한번 본 유저 uuid 리스트"),
                            parameterWithName("userDailyFallingCourserIdx").description("페이징 커서 - 마지막으로 본 UserDailyFalling Idx"),
                            parameterWithName("size").description("페이징 사이즈")
                        )
                        .requestFields()
                        .responseFields(
                            fieldWithPath("[].username").description("유저 이름"),
                            fieldWithPath("[].userUuid").description("유저 uuid"),
                            fieldWithPath("[].age").description("나이"),
                            fieldWithPath("[].address").description("주소"),
                            fieldWithPath("[].isBirthDay").description("생일 여부"),
                            fieldWithPath("[].idealTypeResponseList").description("선택한 이상형 리스트"),
                            fieldWithPath("[].idealTypeResponseList[].idx").description("이상형 idx"),
                            fieldWithPath("[].idealTypeResponseList[].name").description("이상형 명칭"),
                            fieldWithPath("[].idealTypeResponseList[].emojiCode").description("이상형 코드"),
                            fieldWithPath("[].interestResponses").description("선택한 관심사 리스트"),
                            fieldWithPath("[].interestResponses[].idx").description("관심사 idx"),
                            fieldWithPath("[].interestResponses[].name").description("관심사 명칭"),
                            fieldWithPath("[].interestResponses[].emojiCode").description("관심사 코드"),
                            fieldWithPath("[].userProfilePhotos").description("매칭된 유저 프로필 이미지 리스트"),
                            fieldWithPath("[].userProfilePhotos[].url").description("이미지 url"),
                            fieldWithPath("[].userProfilePhotos[].priority").description("사진 우선순위 (1:프로필)"),
                            fieldWithPath("[].introduction").description("자기소개"),
                            fieldWithPath("[].userDailyFallingIdx").description("페이징 커서 - 그날의 토픽을 선택한 유저 정보 idx")
                        )
                        .requestSchema(Schema.schema("UserLoginRequest"))
                        .responseSchema(Schema.schema("UserLoginResponse"))
                        .build()
                ))
        );

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());

    }
}