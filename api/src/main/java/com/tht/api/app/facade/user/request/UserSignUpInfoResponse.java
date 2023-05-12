package com.tht.api.app.facade.user.request;

import com.tht.api.app.entity.enums.SNSType;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public record UserSignUpInfoResponse(
    boolean isSignUp,
    List<String> typeList
) {

    public static UserSignUpInfoResponse of(final boolean isSignUp, final List<String> snsTypes) {

        return new UserSignUpInfoResponse(isSignUp, fitSignUpType(isSignUp, snsTypes));
    }

    private static List<String> fitSignUpType(boolean isSignUp, List<String> snsTypes) {
        if (isSignUp) {
            return Stream.of(List.of(SNSType.NORMAL.name()), snsTypes)
                .flatMap(Collection::stream)
                .toList();
        }
        return snsTypes;
    }
}
