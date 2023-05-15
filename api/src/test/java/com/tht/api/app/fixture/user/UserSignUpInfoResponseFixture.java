package com.tht.api.app.fixture.user;

import com.tht.api.app.entity.enums.SNSType;
import com.tht.api.app.facade.user.request.UserSignUpInfoResponse;
import java.util.Arrays;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserSignUpInfoResponseFixture {

    private static final boolean isSignUp = true;
    private static final List<String> typeList = Arrays.stream(SNSType.values())
        .map(Enum::name)
        .toList();

    public static UserSignUpInfoResponse make() {
        return new UserSignUpInfoResponse(isSignUp, typeList);
    }
}
