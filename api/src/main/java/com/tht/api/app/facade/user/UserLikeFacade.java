package com.tht.api.app.facade.user;

import com.tht.api.app.facade.Facade;
import com.tht.api.app.service.UserLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Facade
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserLikeFacade {

    private final UserLikeService userLikeService;

    @Transactional
    public void doLike(final String myUuid, final String favoriteUserUuid, final long dailyTopicIdx) {

        userLikeService.save(myUuid, favoriteUserUuid, dailyTopicIdx);
    }
}
