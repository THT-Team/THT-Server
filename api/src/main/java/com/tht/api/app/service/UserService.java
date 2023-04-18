package com.tht.api.app.service;

import com.tht.api.app.entity.user.User;
import com.tht.api.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public User createUser(User request) {
        return userRepository.save(request);
    }

    public boolean isExistUserName(final String nickName) {
        return userRepository.existsByUsername(nickName);
    }
}
