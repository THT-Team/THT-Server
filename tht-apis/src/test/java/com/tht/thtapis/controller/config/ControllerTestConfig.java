package com.tht.thtapis.controller.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tht.thtapis.security.TokenProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@AutoConfigureMockMvc
@AutoConfigureRestDocs
@Import({TokenProviderConfig.class})
//@MockitoBean(JpaMetamodelMappingContext.class)
@ExtendWith({RestDocumentationExtension.class})
public abstract class ControllerTestConfig {

    @Autowired
    protected WebApplicationContext ctx;

    @Autowired
    protected ObjectMapper mapper;

    protected static ObjectMapper objectMapper;

    @Autowired
    protected TokenProvider tokenProvider;

    protected static MockMvc mockMvc;


    @BeforeEach
    void setUp(final RestDocumentationContextProvider restDocumentation) {

        objectMapper = mapper;

        mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
            .apply(documentationConfiguration(restDocumentation))
            .addFilters(new CharacterEncodingFilter("UTF-8", true))
            .alwaysDo(print())
            .build();
    }

}
