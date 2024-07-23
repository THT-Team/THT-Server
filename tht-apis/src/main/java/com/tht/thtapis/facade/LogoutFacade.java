package com.tht.thtapis.facade;

import com.tht.domain.entity.user.User;
import com.tht.domain.entity.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Facade
@Transactional
@RequiredArgsConstructor
public class LogoutFacade {

    private final UserService userService;

    public void logout(final User user) {
        userService.logout(user);
    }
}
