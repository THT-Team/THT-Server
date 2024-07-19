package com.tht.thtapis.facade.user.response;

import com.tht.enums.user.SNSType;

import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

public record UserSignUpInfoResponse(
    boolean isSignUp,
    List<String> typeList
) {

    public static UserSignUpInfoResponse ofEnum(final List<SNSType> snsTypes) {

        return new UserSignUpInfoResponse(!snsTypes.isEmpty(), fitSignUpType(snsTypes));
    }

    private static List<String> fitSignUpType(final List<SNSType> snsTypes) {

        if (snsTypes.isEmpty() || snsTypes.contains(SNSType.NORMAL)) {
            return getStrings(snsTypes);
        }

        return getStrings(Stream.of(List.of(SNSType.NORMAL), snsTypes)
            .flatMap(Collection::stream)
            .toList());
    }

    private static List<String> getStrings(final List<SNSType> snsTypes) {
        return snsTypes.stream().map(SNSType::name).toList();
    }
}
