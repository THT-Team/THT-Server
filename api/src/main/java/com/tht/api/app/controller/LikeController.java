package com.tht.api.app.controller;

import com.tht.api.app.entity.user.User;
import com.tht.api.app.facade.like.response.LikeResponse;
import com.tht.api.app.facade.like.LikeFacade;
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

    private final LikeFacade likeFacade;

    @PostMapping("/i-like-you/{favorite-user-uuid}/{daily-topic-idx}")
    public ResponseEntity<LikeResponse> likeYou(
        @AuthenticationPrincipal User user,
        @PathVariable(value = "favorite-user-uuid") String userUuid,
        @PathVariable(value = "daily-topic-idx") long dailyTopicIdx) {

        final LikeResponse response = likeFacade.doLike(user.getUserUuid(), userUuid,
            dailyTopicIdx);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //todo. 대화하기 - 채팅방 생성

    //todo. 내가 받은 좋아요 리스트

}
