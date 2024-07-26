package com.tht.thtadmin.docs;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.tht.thtadmin.docs.config.ControllerTestConfig;
import com.tht.thtadmin.docs.config.WithCustomMockUser;
import com.tht.thtadmin.fixture.user.UserDetailResponseFixture;
import com.tht.thtadmin.fixture.user.UserSimpleListResponseFixture;
import com.tht.thtadmin.ui.user.UserManageController;
import com.tht.thtadmin.ui.user.UserManageUseCase;
import com.tht.thtadmin.ui.user.response.UserDetailResponse;
import com.tht.thtadmin.ui.user.response.UserSimpleListResponse;
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
                    .requestFields()
                    .responseFields(
                        fieldWithPath("content[].username").type(JsonFieldType.STRING).description("유저 이름"),
                        fieldWithPath("content[].createdAt").type(JsonFieldType.STRING).description("생성 시간"),
                        fieldWithPath("content[].userSate").type(JsonFieldType.STRING).description("유저 활동 상태"),
                        fieldWithPath("pageable.sort.empty").description("정렬이 비어있는지 여부"),
                        fieldWithPath("pageable.sort.sorted").description("내용이 정렬되었는지 여부"),
                        fieldWithPath("pageable.sort.unsorted").description("내용이 정렬되지 않았는지 여부"),
                        fieldWithPath("pageable.offset").description("현재 페이지의 오프셋"),
                        fieldWithPath("pageable.pageNumber").description("현재 페이지 번호"),
                        fieldWithPath("pageable.pageSize").description("페이지당 항목 수"),
                        fieldWithPath("pageable.paged").description("페이지 매김이 활성화되었는지 여부"),
                        fieldWithPath("pageable.unpaged").description("페이지 매김이 비활성화되었는지 여부"),
                        fieldWithPath("last").description("마지막 페이지인지 여부"),
                        fieldWithPath("totalElements").description("전체 요소 수"),
                        fieldWithPath("totalPages").description("전체 페이지 수"),
                        fieldWithPath("first").description("첫 번째 페이지인지 여부"),
                        fieldWithPath("size").description("페이지 크기"),
                        fieldWithPath("number").description("현재 페이지 번호"),
                        fieldWithPath("sort.empty").description("정렬이 비어있는지 여부"),
                        fieldWithPath("sort.sorted").description("내용이 정렬되었는지 여부"),
                        fieldWithPath("sort.unsorted").description("내용이 정렬되지 않았는지 여부"),
                        fieldWithPath("numberOfElements").description("현재 페이지의 요소 수"),
                        fieldWithPath("empty").description("현재 페이지가 비어 있는지 여부")
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
                    )
                    .responseSchema(Schema.schema("UserDetailResponse"))
                    .build())
            )
        );
    }
}
