package com.tht.api.app.controller;

import com.tht.api.app.entity.user.User;
import com.tht.api.app.facade.user.UserLikeFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikeController {

    private final UserLikeFacade userLikeFacade;

    @PostMapping("/i-like-you/{favorite-user-uuid}/{daily-topic-idx}")
    public ResponseEntity<Object> likeYou(
        @AuthenticationPrincipal User user,
        @PathVariable(value = "favorite-user-uuid") String userUuid,
        @PathVariable(value = "daily-topic-idx") long dailyTopicIdx) {

        userLikeFacade.doLike(user.getUserUuid(), userUuid, dailyTopicIdx);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //채팅방 생성

    //todo. 내가 받은 좋아요 리스트

}
