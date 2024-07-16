package com.tht.thtapis.service;

import com.tht.domain.auth.UserAuthService;
import com.tht.infra.EntityState;
import com.tht.infra.exception.EntityStateException;
import com.tht.infra.user.User;
import com.tht.infra.user.UserWithDrawLog;
import com.tht.infra.user.enums.Gender;
import com.tht.infra.user.repository.UserRepository;
import com.tht.infra.user.repository.UserWithDrawLogRepository;
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
    private final UserAuthService userAuthService;

    public User createUser(final User user) {
        if (userRepository.existsByPhoneNumber(user.getPhoneNumber())) {
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

    public void updatePhoneNumber(final String userUuid, final String phoneNumber) {
        userAuthService.findByUserUuidForAuthToken(userUuid).updatePhoneNumber(phoneNumber);
    }

    public void updateEmail(final String userUuid, final String email) {
        userAuthService.findByUserUuidForAuthToken(userUuid).updateEmail(email);
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
