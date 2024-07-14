package com.tht.infra.like;

import com.tht.infra.like.fixture.UserLikeFixture;
import com.tht.infra.user.enums.LikeState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UserLikeTest {

    @Test
    @DisplayName("좋아요 거절하기 성공")
    void success_reject() {

        final UserLike userLike = UserLikeFixture.make();
        final String rejectUserUuid = userLike.getTargetUserUuid();

        //when
        userLike.rejectedLike(rejectUserUuid);

        //then
        assertThat(userLike.getLikeState()).isEqualTo(LikeState.REJECT);
    }

    @Test
    @DisplayName("좋아요 거절 실패 : 내가 받은 좋아요가 아닐때")
    void fail_reject_wasNotGivenMine() {

        final long idx = 1;
        final UserLike userLike = UserLikeFixture.make(idx);

        //when
        assertThatThrownBy(() -> userLike.rejectedLike("아무유저uuid"))
            .isInstanceOf(LikeException.class)
            .hasMessageMatching("user uuid : 아무유저uuid 인 유저는 1 래코드 번호의 좋아요를 받은 유저가 아닙니다.");

    }

    @Test
    @DisplayName("좋아요 거절 실패 : 내가 받은 좋아요 대기상태가 아닐 때")
    void fail_reject_likeDontWaitedState() {

        final UserLike userLike = UserLikeFixture.makeDontWait();
        final String rejectUserUuid = userLike.getTargetUserUuid();

        //when
        assertThatThrownBy(() -> userLike.rejectedLike(rejectUserUuid))
            .isInstanceOf(LikeException.class)
            .hasMessageMatching("잘못된 요청입니다. 대기 상태의 좋아요가 아닙니다.");

    }

    @Test
    @DisplayName("좋아요 생성시, likeState 는 WAIT")
    void createDefaultLike() {

        UserLike userLike = UserLikeFixture.make();
        assertThat(userLike.getLikeState()).isEqualTo(LikeState.LIKE);
    }


    @Test
    @DisplayName("싫어요 생성시, likeState 는 DISLIKE")
    void createDisLike() {

        UserLike userLike = UserLikeFixture.makeDisLike();
        assertThat(userLike.getLikeState()).isEqualTo(LikeState.DISLIKE);
    }
}