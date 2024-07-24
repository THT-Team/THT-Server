package com.tht.thtapis.documentation;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.tht.thtapis.controller.config.ControllerTestConfig;
import com.tht.thtapis.controller.config.WithCustomMockUser;
import com.tht.thtapis.facade.LogoutFacade;
import com.tht.thtapis.security.SecurityConst;
import com.tht.thtapis.ui.UserLogoutController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserLogoutController.class)
class UserLogoutDocumentation extends ControllerTestConfig {

    @MockBean
    private LogoutFacade logoutFacade;

    @Test
    @WithCustomMockUser
    @DisplayName("유저 로그아웃 api 문서생성")
    void userLogoutDocs() throws Exception {

        final ResultActions resultActions = mockMvc.perform(post("/user/logout")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .header(SecurityConst.AUTH_HEADER_NAME, "Bearer {ACCESS_TOKEN}")
        ).andDo(
            document("로그아웃 api docs",
                resource(
                    ResourceSnippetParameters.builder()
                        .tag("유저 - 로그아웃")
                        .description("유저 로그아웃 api")
                        .build()
                ))
        );

        resultActions.andExpect(status().isOk());
    }

}
