package com.tht.thtapis.controller;

import com.tht.thtapis.controller.config.ControllerTestConfig;
import com.tht.thtapis.facade.user.AgreementFacade;
import com.tht.thtapis.facade.user.UserJoinFacade;
import com.tht.thtapis.facade.user.request.UserSignUpRequest;
import com.tht.thtapis.facade.user.response.UserSignUpResponse;
import com.tht.thtapis.fixture.user.UserSignUpRequestFixture;
import com.tht.thtapis.ui.UserJoinController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(UserJoinController.class)
class UserJoinControllerTest extends ControllerTestConfig {

    private static final String DEFAULT_URL = "/users/join";
    @MockBean
    UserJoinFacade userJoinFacade;
    @MockBean
    AgreementFacade agreementFacade;

    @Test
    @DisplayName("유저 일반 회원가입 api test(실패) - 관심사 최대 사이즈(3개) 초과 테스트")
    void normalUserJoin_interest_fail() throws Exception {

        //give
        UserSignUpRequest make = UserSignUpRequestFixture.ofInterest(List.of(1, 2, 3, 4));
        String requestBody = objectMapper.writeValueAsString(make);
        when(userJoinFacade.signUp(any())).thenReturn(new UserSignUpResponse("token", 1L));

        //then
        ResultActions resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders.post(DEFAULT_URL + "/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestBody)
        );

        resultActions.andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    @DisplayName("유저 일반 회원가입 api test(실패) - 관심사 최대 사이즈(3개) 초과 테스트")
    void normalUserJoin_idealType_fail() throws Exception {

        //give
        UserSignUpRequest make = UserSignUpRequestFixture.ofIdealType(List.of(1, 2, 3, 4));
        String requestBody = objectMapper.writeValueAsString(make);
        when(userJoinFacade.signUp(any())).thenReturn(new UserSignUpResponse("token", 1L));

        //then
        ResultActions resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders.post(DEFAULT_URL + "/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestBody)
        );

        resultActions.andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @ParameterizedTest
    @ValueSource(ints = {1, 4})
    @DisplayName("유저 일반 회원가입 api test(실패) - 사진은 필수 2장 최대 3장")
    void normalUserJoin_photo_fail(final int size) throws Exception {

        List<String> photo_url = new LinkedList<>();

        for (int i = 0; i < size; i++) {
            photo_url.add(i + "_photo");
        }

        //give
        UserSignUpRequest make = UserSignUpRequestFixture.ofPhoto(photo_url);

        String requestBody = objectMapper.writeValueAsString(make);
        when(userJoinFacade.signUp(any())).thenReturn(new UserSignUpResponse("token", 1L));

        //then
        ResultActions resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders.post(DEFAULT_URL + "/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestBody)
        );

        resultActions.andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @ParameterizedTest
    @ValueSource(strings = {"NORMAL", " ", "df"})
    @DisplayName("SNS 유저 아이디 통합 가입 api test - 유효하지 않은 SNS Type 값 필터링")
    void integratedUserJoin_fail(final String type) throws Exception {

        //give
        String requestBody =
            "{\"phoneNumber\":\"01012341234\",\"deviceKey\":\"device-key\",\"snsType\":\"" + type
                + "\",\"snsUniqueId\":\"sns unique id\"}";

        when(userJoinFacade.integratedSnsId(any())).thenReturn(new UserSignUpResponse("token", 1L));

        //then
        ResultActions resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders.post(DEFAULT_URL + "/signup/sns")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(requestBody)
        );

        resultActions.andExpect(MockMvcResultMatchers.status().isBadRequest());

    }
}
