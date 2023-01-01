package com.tht.api.app.controller.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tht.api.app.facade.request.UserSignUpRequestDTO;
import com.tht.api.app.repository.UserRepository;
import com.tht.api.enums.Gender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Test
    void signUp() throws Exception {

        UserSignUpRequestDTO request = UserSignUpRequestDTO.builder()
                .username("박형민")
                .password("형민123!")
                .phone("01012341234")
                .email("foober@tht.com")
                .gender(Gender.MALE)
                .preferGender(Gender.FEMALE)
                .build();

        String json = objectMapper.writeValueAsString(request);
        String path = "/v1/users/sign-up";

        mockMvc.perform(post(path)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)).andExpect(status().isCreated());
    }
}