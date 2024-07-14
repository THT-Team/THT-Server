package com.tht.thtapis.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import com.tht.thtapis.controller.config.ControllerTestConfig;
import com.tht.thtapis.controller.config.WithCustomMockUser;
import com.tht.thtapis.controller.steps.UserControllerSteps;
import com.tht.thtapis.facade.user.UserFacade;
import com.tht.thtapis.facade.user.request.ModifiedIdealTypeRequest;
import com.tht.thtapis.facade.user.request.ModifiedInterestsRequest;
import com.tht.thtapis.fixture.user.ModifiedIdealTypeRequestFixture;
import com.tht.thtapis.fixture.user.ModifiedInterestsRequestFixture;
import com.tht.thtapis.ui.UserController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(UserController.class)
class UserControllerTest extends ControllerTestConfig {

    @MockBean
    UserFacade userFacade;

    @WithCustomMockUser
    @Test
    @DisplayName("관심사 수정 api 호출 (실패) - 관심사 리스트의 크기는 1~3개 여야한다.")
    void updateInterests_sizeFail() throws Exception {

        ModifiedInterestsRequest request = ModifiedInterestsRequestFixture.ofSize(4);

        var response = UserControllerSteps.유저_관심사_수정_요청(request);

        response.andExpect(MockMvcResultMatchers.status().isBadRequest());
        response.andExpect(jsonPath("message").value("관심사는 최대 3개를 골라주세요"));
    }

    @WithCustomMockUser
    @Test
    @DisplayName("관심사 수정 api 호출 (실패) - 관심사 리스트가 null 이어서는 안된다.")
    void updateInterests_notNull() throws Exception {

        var response = UserControllerSteps.유저_관심사_수정_요청(new ModifiedInterestsRequest(null));

        response.andExpect(MockMvcResultMatchers.status().isBadRequest());
        response.andExpect(jsonPath("message").value("interestList 는 null 이어서는 안됩니다."));
    }

    @WithCustomMockUser
    @Test
    @DisplayName("이상형타입 수정 api 호출 (실패) - 이상형 리스트의 크기는 1~3개 여야한다.")
    void updateIdealType_sizeFail() throws Exception {

        ModifiedIdealTypeRequest request = ModifiedIdealTypeRequestFixture.ofSize(4);

        var response = UserControllerSteps.유저_이상형타입_수정_요청(request);

        response.andExpect(MockMvcResultMatchers.status().isBadRequest());
        response.andExpect(jsonPath("message").value("이상형타입은 최대 3개를 골라주세요"));
    }

    @WithCustomMockUser
    @Test
    @DisplayName("이상형타입 수정 api 호출 (실패) - 이상형 리스트가 null 이어서는 안된다.")
    void updateIdealType_notNull() throws Exception {

        var response = UserControllerSteps.유저_이상형타입_수정_요청(new ModifiedIdealTypeRequest(null));

        response.andExpect(MockMvcResultMatchers.status().isBadRequest());
        response.andExpect(jsonPath("message").value("idealTypeList 는 null 이어서는 안됩니다."));
    }
}