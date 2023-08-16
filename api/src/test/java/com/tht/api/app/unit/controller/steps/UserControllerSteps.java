package com.tht.api.app.unit.controller.steps;

import com.tht.api.app.unit.controller.config.ControllerTestConfig;
import com.tht.api.app.facade.user.request.ModifiedIdealTypeRequest;
import com.tht.api.app.facade.user.request.ModifiedInterestsRequest;
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

    public static ResultActions 유저_이상형타입_수정_요청(ModifiedIdealTypeRequest request) throws Exception {

        String requestBody = objectMapper.writeValueAsString(request);

        return mockMvc.perform(RestDocumentationRequestBuilders.put("/user/ideal-type")
            .header("Authorization", "Bearer {ACCESS_TOKEN}")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(requestBody));

    }
}
