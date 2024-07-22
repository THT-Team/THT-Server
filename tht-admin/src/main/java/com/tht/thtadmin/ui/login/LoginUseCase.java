package com.tht.thtadmin.ui.login;

import com.tht.domain.entity.administrator.Administrator;
import com.tht.domain.entity.administrator.AdministratorService;
import com.tht.thtadmin.security.TokenDto;
import com.tht.thtadmin.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUseCase {

    private final AdministratorService administratorService;
    private final TokenProvider tokenProvider;

    public TokenDto login(final String id, final String password) {
        final Administrator loginInfo = administratorService.getLoginInfo(id, password);
        return tokenProvider.generateJWT(loginInfo);
    }
}
