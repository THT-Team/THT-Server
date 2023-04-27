package com.tht.api.app.service;

import com.tht.api.app.entity.user.UserInterests;
import com.tht.api.app.repository.UserInterestsRepository;
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
        return userInterestsRepository.saveAll(entity);
    }
}
