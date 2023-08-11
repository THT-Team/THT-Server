package com.tht.api.app.service;

import com.tht.api.app.entity.enums.Gender;
import com.tht.api.app.entity.user.User;
import com.tht.api.app.repository.user.UserRepository;
import com.tht.api.exception.custom.EntityStateException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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

        checkDuplicateName(user.getUsername());

        return save(user);
    }

    private void checkDuplicateName(final String name) {
        if (userRepository.existsByUsername(name)) {
            throw EntityStateException.duplicateColumnOf("user", "username");
        }
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

    public User save(final User user) {
        return userRepository.save(user);
    }

    public User updateName(final User user, final String updateNickName) {

        validateUpdateName(updateNickName);

        if (user.isEqualsName(updateNickName)) {
            return user;
        }

        checkDuplicateName(updateNickName);
        user.updateName(updateNickName);

        return save(user);
    }

    private void validateUpdateName(final String name) {
        if (!StringUtils.hasText(name)) {
            throw new IllegalArgumentException("수정할 이름은 공백이어서는 안됩니다.");
        }
    }

    public void updateIntroduction(final User user, final String introduction) {
        user.updateIntroduction(introduction);
        save(user);
    }

    public void updatePreferGender(final User user, final Gender gender) {
        user.updatePreferGender(gender);
        save(user);
    }

    public void withDraw(final User user) {
        user.accountWithdrawal();
        save(user);
    }
}
