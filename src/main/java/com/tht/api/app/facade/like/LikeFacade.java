package com.tht.api.app.facade.like;

import com.tht.api.app.entity.user.UserLike;
import com.tht.api.app.facade.Facade;
import com.tht.api.app.facade.like.response.LikeReceiveResponse;
import com.tht.api.app.facade.like.response.LikeResponse;
import com.tht.api.app.service.ChatRoomService;
import com.tht.api.app.service.ChatRoomUserService;
import com.tht.api.app.service.UserLikeService;
import com.tht.api.app.service.UserService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Facade
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikeFacade {

    private final UserService userService;
    private final UserLikeService userLikeService;
    private final ChatRoomService chatRoomService;
    private final ChatRoomUserService chatRoomUserService;

    @Transactional
    public LikeResponse doLike(final String myUuid, final String favoriteUserUuid, final long dailyTopicIdx) {

        userService.findByUserUuidForAuthToken(favoriteUserUuid);
        final UserLike userLike = userLikeService.save(myUuid, favoriteUserUuid, dailyTopicIdx);

        final Optional<UserLike> isMatchedUserLike = userLikeService.findIsMatchedLike(myUuid,
            favoriteUserUuid, dailyTopicIdx);

        if(isMatchedUserLike.isPresent()){

            long chatRoomIdx = chatRoomService.makeRoomAndGetIdx(dailyTopicIdx);
            chatRoomUserService.inChatRoom(chatRoomIdx, myUuid);
            chatRoomUserService.inChatRoom(chatRoomIdx, favoriteUserUuid);

            isMatchedUserLike.get().matchSuccess();
            userLike.matchSuccess();

            return LikeResponse.matching(chatRoomIdx);
        }

        return LikeResponse.nonMatching();
    }

    @Transactional
    public void dontLike(final String myUuid, final String dontFavoriteUserUuid, final long dailyTopicIdx) {
        userLikeService.disLike(myUuid, dontFavoriteUserUuid, dailyTopicIdx);
    }

    public List<LikeReceiveResponse> getLikedPeopleList(final String userUuid, final int size,
        final Long dailyFallingIdx, final Long likeIdx) {

        return userLikeService.findReceivedLikes(userUuid, size, dailyFallingIdx, likeIdx)
            .stream()
            .map(LikeReceiveResponse::toResponse)
            .toList();
    }

    @Transactional
    public void reject(final String userUuid, final long likeIdx) {

        userLikeService.reject(userUuid, likeIdx);
    }
}
