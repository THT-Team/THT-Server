package com.tht.api.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tht.api.app.entity.user.Gender;
import com.tht.api.app.facade.user.request.UserSignUpRequest;
import com.tht.api.app.facade.user.response.UserFacade;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(UserController.class)
public class UserControllerTest extends ControllerTestConfig {

    private static final String DEFAULT_URL = "/users/signup";

    @MockBean
    UserFacade userFacade;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    @DisplayName("회원가입 POST /signup")
    void signup() throws Exception {

        var request = UserSignUpRequest.builder()
                .username("username")
                .password("password")
                .gender(Gender.MALE)
                .preferGender(Gender.FEMALE)
                .phoneNumber("01012341234")
                .name("hello")
                .email("hello@hello.com")
                .build();

        var json = objectMapper.writeValueAsString(request);

        ResultActions resultActions = mockMvc.perform(
                RestDocumentationRequestBuilders.post(URI.create(DEFAULT_URL))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                MockMvcRestDocumentation.document("interest-docs")
        );

        resultActions.andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
