package com.tht.domain.entity.user.service;

import com.tht.domain.entity.user.User;
import com.tht.domain.entity.user.UserWithDrawLog;
import com.tht.domain.entity.user.exception.UserCustomException;
import com.tht.domain.entity.user.mapper.UserListMapper;
import com.tht.domain.entity.user.repository.UserRepository;
import com.tht.domain.entity.user.repository.UserWithDrawLogRepository;
import com.tht.domain.entity.user.repository.querydsl.mapper.UserDetailMapper;
import com.tht.domain.entity.user.repository.querydsl.mapper.UserWithDrawLogMapper;
import com.tht.domain.entity.user.service.dto.UserDetailDto;
import com.tht.domain.entity.user.service.dto.UserListDto;
import com.tht.domain.entity.user.service.dto.WithDrawUserDto;
import com.tht.domain.exception.EntityStateException;
import com.tht.enums.EntityState;
import com.tht.enums.user.Gender;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

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

    public Page<WithDrawUserDto> getWithDrawUserLog(final Pageable pageable) {

        final Page<UserWithDrawLogMapper> mappers = userWithDrawLogRepository.findAllWithDrawLogList(pageable);
        final List<WithDrawUserDto> result = mappers.getContent().stream().map(WithDrawUserDto::ofMapper).toList();
        return new PageImpl<>(result, pageable, mappers.getTotalElements());
    }

    public void logout(final User user) {

        user.logout();
        save(user);
    }

    public Page<UserListDto> getSimpleUserPageList(final String search, final Pageable pageable) {
        final Page<UserListMapper> userListForPage = userRepository.getUserListForPage(search, pageable);
        final List<UserListDto> result = userListForPage.getContent().stream().map(UserListDto::ofMapper).toList();
        return new PageImpl<>(result, pageable, userListForPage.getTotalElements());
    }

    public UserDetailDto getDetailForAdmin(final String userUuid) {
        final List<UserDetailMapper> detailInfo = userRepository.getDetailInfo(userUuid);

        if (detailInfo.isEmpty()) {
            throw UserCustomException.notExistUuid(userUuid);
        }

        return UserDetailDto.ofMapper(detailInfo);

    }

    public void changeActivateStatus(final String userUuid) {

        final User user = findByUuid(userUuid);
        user.activate();
    }

    private User findByUuid(final String userUuid) {
        return userRepository.findByUserUuid(userUuid).orElseThrow(
            () -> UserCustomException.noneExistUuid(userUuid)
        );
    }

    public void changeInActivateStatus(final String userUuid) {
        final User user = findByUuid(userUuid);
        user.inActivate();
    }
}
