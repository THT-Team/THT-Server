package com.tht.domain.entity.user.service;

import java.util.List;

import com.tht.domain.entity.interesst.InterestMapper;
import com.tht.domain.entity.user.UserInterests;
import com.tht.domain.entity.user.repository.UserInterestsRepository;
import com.tht.thtcommonutils.utils.LogWriteUtils;
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
