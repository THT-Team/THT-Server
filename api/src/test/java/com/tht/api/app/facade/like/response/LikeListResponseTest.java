package com.tht.api.app.facade.like.response;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LikeListResponseTest {

    @DisplayName("좋아요 리스트가 빈 리스트 일 때 size:0, 커서값은 null 을 반환")
    @Test
    void emptyList() {

        List<LikeReceiveResponse> likeList = List.of();

        var response = LikeListResponse.of(likeList);

        assertAll(
            () -> assertThat(response.size()).isZero(),
            () -> assertThat(response.lastLikeIdx()).isNull(),
            () -> assertThat(response.lastFallingTopicIdx()).isNull()
        );
    }
}