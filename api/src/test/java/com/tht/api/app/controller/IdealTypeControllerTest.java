package com.tht.api.app.controller;

import com.tht.api.app.facade.idealtype.IdealTypeFacade;
import java.net.URI;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(IdealTypeController.class)
class IdealTypeControllerTest extends ControllerTestConfig{

    private static final String DEFAULT_URL = "/ideal-types";

    @MockBean
    IdealTypeFacade idealTypeFacade;

    @Test
    @DisplayName("이상형 목록 리스트 조회")
    void getInterestAll() throws Exception {

        ResultActions resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders.get(URI.create(DEFAULT_URL))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(
            MockMvcRestDocumentation.document("ideal-types-docs")
        );

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }
}