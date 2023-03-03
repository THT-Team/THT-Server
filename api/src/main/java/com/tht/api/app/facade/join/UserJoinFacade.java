package com.tht.api.app.facade.join;

import com.tht.api.app.config.RandomUtils;
import com.tht.api.app.config.aligo.AligoUtils;
import com.tht.api.app.facade.Facade;
import com.tht.api.app.facade.join.response.AuthNumberResponse;
import lombok.RequiredArgsConstructor;

@Facade
@RequiredArgsConstructor
public class UserJoinFacade {

    private static final int DIGITS_OF_AUTH_NUMBER = 6;

    public AuthNumberResponse issueAuthenticationNumber(final String phoneNumber) {

        final int authNumber = RandomUtils.getInstance().getFullNumberOfDigits(DIGITS_OF_AUTH_NUMBER);
        AligoUtils.sendAuthNumber(phoneNumber, String.valueOf(authNumber));

        return new AuthNumberResponse(phoneNumber, authNumber);
    }
}

