package com.tht.api.app.controller.steps;

import com.tht.api.app.controller.config.ControllerTestConfig;
import com.tht.api.app.facade.interest.request.ModifiedInterestsRequest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.ResultActions;

public class UserControllerSteps extends ControllerTestConfig {

    public static ResultActions 유저_관심사_수정_요청(ModifiedInterestsRequest request) throws Exception {

        String requestBody = objectMapper.writeValueAsString(request);

        return mockMvc.perform(RestDocumentationRequestBuilders.put("/user/interests")
            .header("Authorization", "Bearer {ACCESS_TOKEN}")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(requestBody));

    }
}
