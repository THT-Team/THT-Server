package com.tht.thtapis.service;

import java.util.List;
import java.util.Optional;

import com.tht.infra.exception.EntityStateException;
import com.tht.infra.user.UserSns;
import com.tht.enums.user.SNSType;
import com.tht.infra.user.exception.UserCustomException;
import com.tht.infra.user.mapper.UserSnsMapper;
import com.tht.infra.user.repository.UserSnsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserSnsService {

    private final UserSnsRepository userSnsRepository;

    public void create(final String userUuid, final SNSType snsType, final String snsUniqueId,
                       final String email) {

        if (userSnsRepository.existsBySnsTypeAndSnsUniqueId(snsType, snsUniqueId)) {
            throw new EntityStateException("해당 " + snsType.name() + " 계정이 이미 존재합니다.");
        }

        userSnsRepository.save(UserSns.create(userUuid, snsType, snsUniqueId, email));
    }

    public List<SNSType> findAllByPhoneNumber(final String phoneNumber) {

        final Optional<List<UserSnsMapper>> userSnsList = userSnsRepository.findAllByPhoneNumber(
            phoneNumber);

        return userSnsList.map(mappers -> mappers.stream().map(UserSnsMapper::snsType).toList())
            .orElseGet(List::of);
    }

    public boolean isExistIntegratedUserInfo(final String userUuid, final SNSType snsType, final String snsUniqueId) {

        return userSnsRepository.existsByUserUuidAndSnsTypeOrSnsTypeAndSnsUniqueId(userUuid, snsType, snsType, snsUniqueId);
    }

    public String findUserUuidBySnsIdKey(final SNSType snsType, final String snsUniqueId) {
        return userSnsRepository.findUserUuidBySnsIdKey(snsType, snsUniqueId)
            .orElseThrow(UserCustomException::notExist);
    }
}
