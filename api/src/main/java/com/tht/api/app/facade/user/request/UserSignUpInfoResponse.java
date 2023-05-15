package com.tht.api.app.facade.user.request;

import com.tht.api.app.entity.enums.SNSType;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public record UserSignUpInfoResponse(
    boolean isSignUp,
    List<String> typeList
) {

    public static UserSignUpInfoResponse of(final List<String> snsTypes) {

        return new UserSignUpInfoResponse(!snsTypes.isEmpty(), fitSignUpType(snsTypes));
    }

    private static List<String> fitSignUpType(final List<String> snsTypes) {

        if (snsTypes.isEmpty()) {
            return snsTypes;
        }

        if (snsTypes.contains(SNSType.NORMAL.name())) {
            return Stream.of(snsTypes)
                .flatMap(Collection::stream)
                .toList();
        }

        return Stream.of(List.of(SNSType.NORMAL.name()), snsTypes)
            .flatMap(Collection::stream)
            .toList();
    }
}
