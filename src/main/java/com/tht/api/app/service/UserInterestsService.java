package com.tht.api.app.service;

import com.tht.api.app.config.utils.LogWriteUtils;
import com.tht.api.app.entity.user.UserInterests;
import com.tht.api.app.repository.mapper.InterestMapper;
import com.tht.api.app.repository.user.UserInterestsRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserInterestsService {

    private final UserInterestsRepository userInterestsRepository;

    public List<UserInterests> createOf(final List<UserInterests> entity) {
        LogWriteUtils.logInfo("new_user_interests : " + entity);
        return userInterestsRepository.saveAll(entity);
    }

    public List<InterestMapper> findBy(final String userUuid) {
        return userInterestsRepository.findInterestInfoBy(userUuid);
    }

    public void update(final String userUuid, final List<Integer> interestList) {

        userInterestsRepository.deleteAllByUserUuid(userUuid);
        userInterestsRepository.saveAll(
            interestList.stream()
                .map(idx -> UserInterests.create(userUuid, idx))
                .toList()
        );
    }
}
