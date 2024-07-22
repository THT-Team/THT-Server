package com.tht.domain.entity.user.service;

import com.tht.enums.EntityState;
import com.tht.enums.user.Gender;
import com.tht.domain.entity.user.exception.UserCustomException;
import com.tht.domain.entity.user.repository.UserRepository;
import com.tht.domain.exception.EntityStateException;
import com.tht.domain.entity.user.User;
import com.tht.domain.entity.user.UserWithDrawLog;
import com.tht.domain.entity.user.repository.UserWithDrawLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserWithDrawLogRepository userWithDrawLogRepository;

    public User createUser(final User user) {
        if (userRepository.existsByPhoneNumberAndStateEquals(user.getPhoneNumber(), EntityState.ACTIVE)) {
            throw EntityStateException.duplicateColumnOf(user.getClass().getSimpleName(),
                "phoneNumber");
        }

        checkDuplicateName(user.getUsername());

        return save(user);
    }

    private void checkDuplicateName(final String name) {
        if (userRepository.existsByUsernameAndStateEquals(name, EntityState.ACTIVE)) {
            throw EntityStateException.duplicateColumnOf("user", "username");
        }
    }

    public boolean isExistUserName(final String nickName) {
        return userRepository.existsByUsernameAndStateEquals(nickName, EntityState.ACTIVE);
    }

    public User findByPhoneNumber(final String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber)
            .orElseThrow(() -> UserCustomException.noneValidPhoneNumberToAuth(phoneNumber));
    }

    public User findByUserUuidForAuthToken(final String userUuid) {
        return userRepository.findByUserUuid(userUuid)
            .orElseThrow(() -> UserCustomException.noneValidUuidToAuth(userUuid));
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

    public void withDraw(final User user, final String reason, final String feedBack) {

        user.accountWithdrawal();
        userWithDrawLogRepository.save(UserWithDrawLog.of(user.getUserUuid(), reason, feedBack));
        save(user);
    }
}
