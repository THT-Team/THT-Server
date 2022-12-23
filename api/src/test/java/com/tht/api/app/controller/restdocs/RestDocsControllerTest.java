package com.tht.api.app.controller.restdocs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.request.RequestDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@AutoConfigureRestDocs
@WebMvcTest(RestDocsController.class)
class RestDocsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Spring Rest Docs Test")
    void restDocsTest() throws Exception {

        String text = "parameter test";

        ResultActions resultActions = mockMvc.perform(
            RestDocumentationRequestBuilders.get("/rest-docs/{text}", text)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andDo(
            MockMvcRestDocumentation.document("guide-rest-docs",
                RequestDocumentation.pathParameters(
                    RequestDocumentation.parameterWithName("text").description("파라미터 1")
                )
            )
        );

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }
}