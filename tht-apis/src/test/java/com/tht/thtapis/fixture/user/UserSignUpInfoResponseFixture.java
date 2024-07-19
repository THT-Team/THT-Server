package com.tht.thtapis.fixture.user;

import java.util.Arrays;
import java.util.List;

import com.tht.enums.user.SNSType;
import com.tht.thtapis.facade.user.response.UserSignUpInfoResponse;
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
