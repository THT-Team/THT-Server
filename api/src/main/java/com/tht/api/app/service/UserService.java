package com.tht.api.app.service;

import com.tht.api.app.entity.user.User;
import com.tht.api.app.repository.user.UserRepository;
import com.tht.api.exception.custom.EntityStateException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public User createUser(final User user) {
        if (userRepository.existsByPhoneNumber(user.getPhoneNumber())) {
            throw EntityStateException.duplicateColumnOf(user.getClass().getSimpleName(),
                "phoneNumber");
        }

        if (userRepository.existsByUsername(user.getUsername())) {
            throw EntityStateException.duplicateColumnOf(user.getClass().getSimpleName(),
                "username");
        }

        return userRepository.save(user);
    }

    public boolean isExistUserName(final String nickName) {
        return userRepository.existsByUsername(nickName);
    }

    public User findByPhoneNumber(final String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber)
            .orElseThrow(() -> new BadCredentialsException("존재하지 않는 유저 번호입니다."));
    }

    public User findByUserUuidForAuthToken(final String userUuid) {
        return userRepository.findByUserUuid(userUuid)
            .orElseThrow(() -> new BadCredentialsException("존재하지 않는 회원번호 입니다."));
    }

    public void updatePhoneNumber(final String userUuid, final String phoneNumber) {
        findByUserUuidForAuthToken(userUuid).updatePhoneNumber(phoneNumber);
    }

    public void updateEmail(final String userUuid, final String email) {
        findByUserUuidForAuthToken(userUuid).updateEmail(email);
    }
}
