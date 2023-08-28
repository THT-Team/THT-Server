package com.tht.api.app.service;

import com.tht.api.app.entity.user.UserLike;
import com.tht.api.app.repository.user.UserLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserLikeService {

    private final UserLikeRepository userLikeRepository;

    @Transactional
    public void save(final String myUuid, final String favoriteUserUuid, final long dailyTopicIdx) {
        userLikeRepository.save(UserLike.create(myUuid, favoriteUserUuid, dailyTopicIdx));
    }
}
