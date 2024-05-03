package com.tht.api.app.documentation;

import com.epages.restdocs.apispec.ResourceSnippetParameters;
import com.epages.restdocs.apispec.Schema;
import com.tht.api.app.controller.DailyFallingController;
import com.tht.api.app.controller.config.ControllerTestConfig;
import com.tht.api.app.controller.config.WithCustomMockUser;
import com.tht.api.app.entity.enums.DailyFallingType;
import com.tht.api.app.facade.main.DailyFallingFacade;
import com.tht.api.app.facade.main.response.DailyTopicChooseResponse;
import com.tht.api.app.fixture.main.DailyFallingResponseFixture;
import com.tht.api.app.fixture.main.TalkKeywordFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static com.epages.restdocs.apispec.ResourceDocumentation.resource;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.when;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

@WebMvcTest(DailyFallingController.class)
class DailyFallingDocumentation extends ControllerTestConfig {

    @MockBean
    DailyFallingFacade dailyFallingFacade;

    @Test
    @DisplayName("오늘의 폴링 주제 리스트 조회 api - docs")
    void getDailyFallingList() throws Exception {

        //give
        when(dailyFallingFacade.getDailyFallingList())
                .thenReturn(DailyFallingResponseFixture.make());

        //then
        ResultActions resultActions = mockMvc.perform(
                get("/falling/daily-keyword")
                        .header("Authorization", "Bearer {ACCESS_TOKEN}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                document("오늘의 폴링 토픽 리스트 조회 docs",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(
                                ResourceSnippetParameters.builder()
                                        .tag("토픽")
                                        .description("오늘의 폴링 토픽 리스트 조회")
                                        .requestFields()
                                        .responseFields(
                                                fieldWithPath("expirationUnixTime").type(JsonFieldType.NUMBER)
                                                        .description("폴링 주제어 만료시간 [존재하지 않으면 -1]"),
                                                fieldWithPath("type").type(JsonFieldType.STRING)
                                                        .description(String.format("그날의 대화주제 표출 형식 - ex %s", EnumDocsUtils.getTypesFieldList(DailyFallingType.class))),
                                                fieldWithPath("fallingTopicList").type(JsonFieldType.ARRAY)
                                                        .description("폴링 주제어 라수투 [존재하지 않으면 빈 리스트 [] "),
                                                fieldWithPath("fallingTopicList[].idx").type(JsonFieldType.NUMBER).description("데일리 폴링 토픽 idx"),
                                                fieldWithPath("fallingTopicList[].keyword").type(JsonFieldType.STRING).description("폴링 주제어"),
                                                fieldWithPath("fallingTopicList[].keywordIdx").type(JsonFieldType.NUMBER).description("폴링 주제어 idx"),
                                                fieldWithPath("fallingTopicList[].keywordImgUrl").type(JsonFieldType.STRING).description("폴링 주제어 이미지 url"),
                                                fieldWithPath("fallingTopicList[].talkIssue").type(JsonFieldType.STRING).description("주제어 파생 질문")
                                        )
                                        .responseSchema(Schema.schema("DailyFallingResponse"))
                                        .build()
                        )
                ));

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithCustomMockUser
    @DisplayName("오늘의 폴링 주제 선택 api - docs")
    void chooseDailyFallingTopic() throws Exception {

        //give
        long dailyFallingIdx = 11003;

        //then
        ResultActions resultActions = mockMvc.perform(
                post("/falling/choice/daily-keyword/{daily-falling-idx}", dailyFallingIdx)
                        .header("Authorization", "Bearer {ACCESS_TOKEN}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                document("오늘의 폴링 토픽 선택 docs",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(
                                ResourceSnippetParameters.builder()
                                        .tag("토픽")
                                        .description("오늘의 폴링 토픽 선택")
                                        .requestFields()
                                        .responseFields()
                                        .build()
                        )
                ));

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("삭제되지 않은 전체 키워드 조회 api - docs")
    void getAllKeywordAtActive() throws Exception {

        //give
        when(dailyFallingFacade.getTalkKeywords())
                .thenReturn(TalkKeywordFixture.makeList());

        //then
        ResultActions resultActions = mockMvc.perform(
                get("/all/talk-keyword")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                document("모든 토픽 키워드 리스트 조회 docs",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(
                                ResourceSnippetParameters.builder()
                                        .tag("토픽")
                                        .description("모든 토픽 키워드 리스트 조회")
                                        .requestFields()
                                        .responseFields(
                                                fieldWithPath("[].idx").description("토픽 Idx"),
                                                fieldWithPath("[].keyword").description("토픽 주제어"),
                                                fieldWithPath("[].keywordImgIdx").description("토픽 이미지 idx")
                                        )
                                        .responseSchema(Schema.schema("TalkKeywordResponse"))
                                        .build()
                        )
                ));

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("유저의 토픽선택여부 확인 api document")
    void isChooseUserDailTopic() throws Exception {

        //give


        when(dailyFallingFacade.checkIsChooseDailyTopicUser(any()))
                .thenReturn(DailyTopicChooseResponse.isChooseDone());

        //then
        ResultActions resultActions = mockMvc.perform(
                get("/check/is-choose-daily-topic")
                        .header("Authorization", "Bearer {ACCESS_TOKEN}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(
                document("유저가 그날의 대화주제를 선택했는지 여부 조회 docs",
                        preprocessRequest(prettyPrint()),
                        preprocessResponse(prettyPrint()),
                        resource(
                                ResourceSnippetParameters.builder()
                                        .tag("토픽")
                                        .description("유저의 그날의 대화주제 선택 여부 확인 api")
                                        .requestFields()
                                        .responseFields(
                                                fieldWithPath("isChoose").type(JsonFieldType.BOOLEAN)
                                                        .description("오늘의 대화주제 선택 여부")
                                        )
                                        .responseSchema(Schema.schema("DailyTopicChooseResponse"))
                                        .build()
                        )
                )
        );

        resultActions.andExpect(MockMvcResultMatchers.status().isOk());
    }
}