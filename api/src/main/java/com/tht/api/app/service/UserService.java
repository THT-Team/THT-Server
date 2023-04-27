package com.tht.api.app.service;

import com.tht.api.app.entity.user.User;
import com.tht.api.app.repository.UserRepository;
import com.tht.api.exception.custom.EntityStateException;
import lombok.RequiredArgsConstructor;
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
}
