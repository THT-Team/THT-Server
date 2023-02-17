package com.tht.api.app.facade.join;

import com.tht.api.app.config.RandomUtils;
import com.tht.api.app.facade.Facade;
import com.tht.api.app.facade.join.response.AuthNumberResponse;
import com.tht.api.app.service.AligoService;
import java.util.Base64;
import lombok.RequiredArgsConstructor;

@Facade
@RequiredArgsConstructor
public class UserJoinFacade {

    private static final int DIGITS_OF_AUTH_NUMBER = 6;

    private final AligoService aligoService;

    public AuthNumberResponse issueAuthenticationNumber(final String phoneNumber) {

        final String authNumber = String.valueOf(
            RandomUtils.getInstance().getFullNumberOfDigits(DIGITS_OF_AUTH_NUMBER));

        aligoService.sendAuthNumber(phoneNumber, authNumber);

        final String encodeAuthNumber = Base64.getEncoder().encodeToString(authNumber.getBytes());
        return new AuthNumberResponse(phoneNumber, encodeAuthNumber);
    }
}
