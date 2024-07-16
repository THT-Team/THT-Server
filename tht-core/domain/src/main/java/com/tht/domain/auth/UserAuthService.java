package com.tht.domain.auth;

import com.tht.infra.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserAuthService {

    private final UserAuthRepository userRepository;

    public User findByPhoneNumber(final String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber)
            .orElseThrow(() -> new UnAuthException("존재하지 않는 유저 번호입니다."));
    }

    public User findByUserUuidForAuthToken(final String userUuid) {
        return userRepository.findByUserUuid(userUuid)
            .orElseThrow(() -> new UnAuthException("존재하지 않는 회원번호 입니다."));
    }
}
