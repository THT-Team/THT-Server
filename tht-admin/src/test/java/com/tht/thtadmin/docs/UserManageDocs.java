package com.tht.thtadmin.docs;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.tht.enums.EnumDocsUtils;
import com.tht.enums.user.Gender;
import com.tht.enums.user.UserFrequency;
import com.tht.enums.user.UserReligion;
import com.tht.thtadmin.docs.config.ControllerTestConfig;
import com.tht.thtadmin.docs.config.WithCustomMockUser;
import com.tht.thtadmin.fixture.user.WithDrawUserResponseFixture;
import com.tht.thtadmin.fixture.user.UserBlockResponseFixture;
import com.tht.thtadmin.fixture.user.UserDetailResponseFixture;
import com.tht.thtadmin.fixture.user.UserReportResponseFixture;
import com.tht.thtadmin.fixture.user.UserSimpleListResponseFixture;
import com.tht.thtadmin.ui.user.UserManageController;
import com.tht.thtadmin.ui.user.UserManageUseCase;
import com.tht.thtadmin.ui.user.response.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static com.tht.thtadmin.docs.config.DocsConst.getPagingFieldDescriptors;
import static org.mockito.Mockito.*;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;

@WebMvcTest(UserManageController.class)
class UserManageDocs extends ControllerTestConfig {

    @MockBean
    UserManageUseCase userManageUseCase;

    @Test
    @WithCustomMockUser
    @DisplayName("전체 유저 조회 api docs")
    void getUsers() throws Exception {

        UserSimpleListResponse response = UserSimpleListResponseFixture.make();
        when(userManageUseCase.getUserList(anyString(), any()))
            .thenReturn(new PageImpl<>(List.of(response), PageRequest.of(0, 10), 1));

        ResultActions resultActions = mockMvc.perform(
                get("/users?page=0&size=10&search=")
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", "Bearer {ACCESS_TOKEN}")
            )
            .andDo(document("전체 회원 조회 및 검색 api",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(ResourceSnippetParameters.builder()
                    .tag("회원 관리")
                    .description("전체 회원 리스트 조회 및 검색")
                    .queryParameters(
                        parameterWithName("search").optional().description("검색 이름"),
                        parameterWithName("size").optional().description("페이지 사이즈"),
                        parameterWithName("page").optional().description("검색할 페이지"),
                        parameterWithName("sort").optional().ignored().description("정렬 타입"),
                        parameterWithName("direction").optional().ignored().description("정렬 방식")
                    )
                    .responseFields(
                        getPagingFieldDescriptors(List.of(
                            fieldWithPath("content[].username").type(JsonFieldType.STRING).description("유저 이름"),
                            fieldWithPath("content[].userUuid").type(JsonFieldType.STRING).description("유저 고유 번호"),
                            fieldWithPath("content[].createdAt").type(JsonFieldType.STRING).description("생성 시간"),
                            fieldWithPath("content[].userSate").type(JsonFieldType.STRING).description("유저 활동 상태")
                        ))
                    )
                    .responseSchema(Schema.schema("UserSimpleListResponse"))
                    .build())
            ));

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithCustomMockUser
    @DisplayName("회원 상세 조회")
    void getUser() throws Exception {

        final String userUuid = "user-uuid";
        UserDetailResponse response = UserDetailResponseFixture.make();

        when(userManageUseCase.getUserDetail(anyString())).thenReturn(response);

        //then
        mockMvc.perform(
            get("/user/{user-uuid}", userUuid)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer {ACCESS_TOKEN}")

        ).andDo(document("회원 정보 상세 조회 api",
                preprocessRequest(prettyPrint()),
                preprocessResponse(prettyPrint()),
                resource(ResourceSnippetParameters.builder()
                    .tag("회원 관리")
                    .description("회원 정보 상세 조회")
                    .pathParameters(
                        parameterWithName("user-uuid").description("유저 고유 넘버")
                    )
                    .requestFields()
                    .responseFields(
                        fieldWithPath("phoneNumber").description("회원 전화번호"),
                        fieldWithPath("username").description("회원 닉네임"),
                        fieldWithPath("birthDay").description("생년월일"),
                        fieldWithPath("email").description("이메일"),
                        fieldWithPath("snsSignUpList").description("SNS 통합 회원가입 리스트"),
                        fieldWithPath("serviceAgreeList").description("서비스 이용약관 동의 리스트"),
                        fieldWithPath("serviceAgreeList.PERSONAL_PRIVACY_INFO_AGREE").description("개인정보 이용약관 동의 여부"),
                        fieldWithPath("serviceAgreeList.LOCATION_SERVICE_AGREE").description("위치정보 이용약관 동의 여부"),
                        fieldWithPath("serviceAgreeList.SERVICE_USE_AGREE").description("서비스 이용약관 동의 여부"),
                        fieldWithPath("serviceAgreeList.MARKETING_AGREE").description("마케팅 정보 이용약관 동의 여부"),
                        fieldWithPath("gender").description(String.format("성별 [%s]", EnumDocsUtils.getTypesFieldList(Gender.class))),
                        fieldWithPath("preferGender").description(String.format("선호성별 [%s]", EnumDocsUtils.getTypesFieldList(Gender.class))),
                        fieldWithPath("profileUrl").description("회원 프로필 사진"),
                        fieldWithPath("profileUrl.1").description("대표 사진"),
                        fieldWithPath("profileUrl.2").description("사진 1"),
                        fieldWithPath("profileUrl.3").description("사진 2"),
                        fieldWithPath("tall").description("키"),
                        fieldWithPath("drinkStatus").description(String.format("음주 여부 [%s]", EnumDocsUtils.getTypesFieldList(UserFrequency.class))),
                        fieldWithPath("religion").description(String.format("종교 [%s]", EnumDocsUtils.getTypesFieldList(UserReligion.class))),
                        fieldWithPath("smokingStatus").description(String.format("흡연 여부 [%s]", UserFrequency.class)),
                        fieldWithPath("userLocation").description("유저 집주소"),
                        fieldWithPath("interests").description("유저가 선택한 관심사 목록"),
                        fieldWithPath("idealTypes").description("유저가 선택한 이상형 목록")
                    )
                    .responseSchema(Schema.schema("UserDetailResponse"))
                    .build())
            )
        );
    }

    @Test
    @DisplayName("회원 차단 목록 리스트")
    @WithCustomMockUser
    void blockUsers() throws Exception {

        UserBlockResponse response = UserBlockResponseFixture.make();
        PageImpl<UserBlockResponse> page = new PageImpl<>(List.of(response), PageRequest.of(0, 10), 1);

        when(userManageUseCase.getBlockUserList(any())).thenReturn(page);

        //then
        ResultActions resultActions = mockMvc.perform(
            get("/users/block")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer {ACCESS_TOKEN}")
        ).andDo(document("회원 차단 목록 리스트",
            preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint()),
            resource(ResourceSnippetParameters.builder()
                .tag("회원 관리")
                .description("회원 차단 목록 리스트")
                .queryParameters(
                    parameterWithName("size").optional().description("페이지 사이즈"),
                    parameterWithName("page").optional().description("검색할 페이지"),
                    parameterWithName("sort").optional().ignored().description("정렬 타입"),
                    parameterWithName("direction").optional().ignored().description("정렬 방식")
                )
                .responseFields(
                    getPagingFieldDescriptors(List.of(
                        fieldWithPath("content[].username").type(JsonFieldType.STRING).description("유저 이름"),
                        fieldWithPath("content[].userUuid").type(JsonFieldType.STRING).description("유저 고유 번호"),
                        fieldWithPath("content[].currentBlockDate").type(JsonFieldType.STRING).description("가장 최근 차단 시간"),
                        fieldWithPath("content[].userStatus").type(JsonFieldType.STRING).description("유저 활동 상태"),
                        fieldWithPath("content[].gender").type(JsonFieldType.STRING).description("유저 성별"),
                        fieldWithPath("content[].blockedUserName").type(JsonFieldType.STRING).description("신고한 유저 이름")
                    ))
                )
                .responseSchema(Schema.schema("UserBlockResponse"))
                .build())
        ));

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("회원 신고 목록 리스트")
    @WithCustomMockUser
    void reportUsers() throws Exception {

        UserReportResponse response = UserReportResponseFixture.make();
        PageImpl<UserReportResponse> page = new PageImpl<>(List.of(response), PageRequest.of(0, 10), 1);

        when(userManageUseCase.getReportUserList(any())).thenReturn(page);

        //then
        ResultActions resultActions = mockMvc.perform(
            get("/users/report")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer {ACCESS_TOKEN}")
        ).andDo(document("회원 신고 목록 리스트",
            preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint()),
            resource(ResourceSnippetParameters.builder()
                .tag("회원 관리")
                .description("회원 신고 목록 리스트")
                .queryParameters(
                    parameterWithName("size").optional().description("페이지 사이즈"),
                    parameterWithName("page").optional().description("검색할 페이지"),
                    parameterWithName("sort").optional().ignored().description("정렬 타입"),
                    parameterWithName("direction").optional().ignored().description("정렬 방식")
                )
                .responseFields(getPagingFieldDescriptors(List.of(
                    fieldWithPath("content[].username").type(JsonFieldType.STRING).description("유저 이름"),
                    fieldWithPath("content[].userUuid").type(JsonFieldType.STRING).description("유저 고유 번호"),
                    fieldWithPath("content[].reportDate").type(JsonFieldType.STRING).description("가장 최근 신고당한 시간"),
                    fieldWithPath("content[].userStatus").type(JsonFieldType.STRING).description("유저 활동 상태"),
                    fieldWithPath("content[].gender").type(JsonFieldType.STRING).description("유저 성별"),
                    fieldWithPath("content[].preferGender").type(JsonFieldType.STRING).description("선호 성별"),
                    fieldWithPath("content[].reportedUserName").type(JsonFieldType.STRING).description("신고한 유저 이름"),
                    fieldWithPath("content[].reason").type(JsonFieldType.STRING).description("신고한 이유")
                )))
                .responseSchema(Schema.schema("UserBlockResponse"))
                .build())
        ));

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("탈퇴 회원 목록 리스트")
    @WithCustomMockUser
    void getWithDrawUsers() throws Exception {

        WithDrawUserResponse response = WithDrawUserResponseFixture.make();
        PageImpl<WithDrawUserResponse> page = new PageImpl<>(List.of(response), PageRequest.of(0, 10), 1);

        when(userManageUseCase.getWithDrawList(any())).thenReturn(page);

        //then
        ResultActions resultActions = mockMvc.perform(
            get("/users/withdraw")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer {ACCESS_TOKEN}")
        ).andDo(document("탈퇴 요청 회원 목록 리스트",
            preprocessRequest(prettyPrint()),
            preprocessResponse(prettyPrint()),
            resource(ResourceSnippetParameters.builder()
                .tag("회원 관리")
                .description("회원 신고 목록 리스트")
                .queryParameters(
                    parameterWithName("size").optional().description("페이지 사이즈"),
                    parameterWithName("page").optional().description("검색할 페이지"),
                    parameterWithName("sort").optional().ignored().description("정렬 타입"),
                    parameterWithName("direction").optional().ignored().description("정렬 방식")
                )
                .responseFields(getPagingFieldDescriptors(List.of(
                    fieldWithPath("content[].username").type(JsonFieldType.STRING).description("유저 이름"),
                    fieldWithPath("content[].userUuid").type(JsonFieldType.STRING).description("유저 고유 번호"),
                    fieldWithPath("content[].requestDate").type(JsonFieldType.STRING).description("탈퇴 요청 시간"),
                    fieldWithPath("content[].userStatus").type(JsonFieldType.STRING).description("유저 활동 상태"),
                    fieldWithPath("content[].feedBack").type(JsonFieldType.STRING).description("피드백"),
                    fieldWithPath("content[].reason").type(JsonFieldType.STRING).description("탈퇴 이유")
                )))
                .responseSchema(Schema.schema("UserBlockResponse"))
                .build())
        ));

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }



}
