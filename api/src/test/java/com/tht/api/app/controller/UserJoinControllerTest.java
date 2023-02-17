package com.tht.api.app.controller;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;

import com.tht.api.app.facade.join.UserJoinFacade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(UserJoinController.class)
class UserJoinControllerTest extends ControllerTestConfig {

    @MockBean
    UserJoinFacade userJoinFacade;

    @Test
    @DisplayName("유저 번호 인증 발급")
    void getCertificationNumber() throws Exception {

        //given
        String phoneNumber = "01012345678";

        ResultActions resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders.get("/user/certification/phone-number/{phone-number}",
                    phoneNumber)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(
            document("user-certification-number-docs",
                pathParameters(parameterWithName("phone-number").description("유저 폰 번호")),
                responseFields(
                    fieldWithPath("phoneNumber").description("핸드폰 번호"),
                    fieldWithPath("authNumber").description("인코딩 된 인증번호")
                )
            )
        );

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());

    }
}