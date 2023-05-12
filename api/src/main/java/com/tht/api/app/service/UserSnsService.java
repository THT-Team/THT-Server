package com.tht.api.app.service;

import com.tht.api.app.entity.enums.SNSType;
import com.tht.api.app.entity.user.UserSns;
import com.tht.api.app.repository.UserSnsRepository;
import com.tht.api.app.repository.mapper.UserSnsMapper;
import com.tht.api.exception.custom.EntityStateException;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserSnsService {

    private final UserSnsRepository userSnsRepository;

    public void create(final String userUuid, final SNSType snsType, final String snsUniqueId) {
        if (userSnsRepository.existsBySnsTypeAndSnsUniqueId(snsType, snsUniqueId)) {
            throw new EntityStateException("해당 " +snsType.name() + " 계정이 이미 존재합니다.");
        }

        userSnsRepository.save(UserSns.create(userUuid, snsType, snsUniqueId));
    }

    public List<String> findAllByPhoneNumber(final String phoneNumber) {

        final Optional<List<UserSnsMapper>> userSnsList = userSnsRepository.findAllByPhoneNumber(
            phoneNumber);

        return userSnsList.map(
                sns -> sns.stream().map(userSns -> userSns.getSnsType().name()).toList())
            .orElseGet(List::of);
    }

}
