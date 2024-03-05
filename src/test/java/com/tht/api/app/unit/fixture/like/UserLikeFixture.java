package com.tht.api.app.unit.fixture.like;

import static org.mockito.BDDMockito.spy;
import static org.mockito.BDDMockito.when;

import com.tht.api.app.entity.user.UserLike;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserLikeFixture {

    private static final String userUuid = "user-uuid";
    private static final String favoriteUserUuid = "favorite-user-uuid";
    private static final long dailyFallingIdx = 1;
    private static final int idx = 1;

    private static UserLike create(String userUuid, String favoriteUserUuid, long dailyFallingIdx, long idx) {

        UserLike userLike = UserLike.create(userUuid, favoriteUserUuid, dailyFallingIdx);

        UserLike spy = spy(userLike);
        when(spy.getIdx()).thenReturn(idx);

        return spy;
    }

    public static UserLike make() {
        return create(userUuid, favoriteUserUuid, dailyFallingIdx, idx);
    }

    public static UserLike make(long idx) {
        return create(userUuid, favoriteUserUuid, dailyFallingIdx, idx);
    }

    public static UserLike makeDontWait() {
        UserLike make = make();
        make.matchSuccess();
        return make;
    }

    public static UserLike makeDisLike() {
        return UserLike.disLike(userUuid, favoriteUserUuid, dailyFallingIdx);
    }
}
