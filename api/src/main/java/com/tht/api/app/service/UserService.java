package com.tht.api.app.service;

import com.tht.api.app.entity.User;
import com.tht.api.app.facade.request.UserSignUpRequestDTO;
import com.tht.api.app.facade.respone.UserResponseDTO;
import com.tht.api.app.repository.UserRepository;
import com.tht.api.enums.Gender;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User signup(@Valid UserSignUpRequestDTO request) {
        return userRepository.save(User.builder()
                .username(request.getUsername())
                .passwordHash(request.getPassword())
                .isEmailVerified(false)
                .phone(request.getPhone())
                .isPhoneVerified(false)
                .gender(request.getGender())
                .preferGender(request.getPreferGender())
                .build());
    }
}
