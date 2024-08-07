package com.tht.thtapis.ui;

import com.tht.domain.entity.user.User;
import com.tht.thtapis.facade.like.LikeFacade;
import com.tht.thtapis.facade.like.response.LikeListResponse;
import com.tht.thtapis.facade.like.response.LikeReceiveResponse;
import com.tht.thtapis.facade.like.response.LikeResponse;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping("/i-dont-like-you/{dont-favorite-user-uuid}/{daily-topic-idx}")
    public ResponseEntity<Objects> dontLikeYou(
        @AuthenticationPrincipal User user,
        @PathVariable(value = "dont-favorite-user-uuid") String userUuid,
        @PathVariable(value = "daily-topic-idx") long dailyTopicIdx) {

        likeFacade.dontLike(user.getUserUuid(), userUuid, dailyTopicIdx);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/like/receives")
    public ResponseEntity<LikeListResponse> getLikeList(
        @AuthenticationPrincipal User user,
        @RequestParam(value = "size", defaultValue = "100") int size,
        @RequestParam(value = "lastFallingTopicIdx", required = false) Long dailyFallingIdx,
        @RequestParam(value = "lastLikeIdx", required = false) Long likeIdx) {

        final List<LikeReceiveResponse> likedPeopleList = likeFacade.getLikedPeopleList(
            user.getUserUuid(), size, dailyFallingIdx, likeIdx);

        return ResponseEntity.ok(LikeListResponse.of(likedPeopleList));
    }

    @PostMapping("/like/reject")
    public ResponseEntity<Objects> rejectLike(
        @AuthenticationPrincipal User user,
        @RequestParam(value = "likeIdx") long likeIdx) {

        likeFacade.reject(user.getUserUuid(), likeIdx);
        return ResponseEntity.ok().build();
    }
}
