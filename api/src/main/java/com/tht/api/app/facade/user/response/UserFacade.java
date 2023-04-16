package com.tht.api.app.facade.user.response;

import com.tht.api.app.entity.user.User;
import com.tht.api.app.facade.Facade;
import com.tht.api.app.facade.user.UserResponse;
import com.tht.api.app.facade.user.request.UserSignUpRequest;
import com.tht.api.app.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Facade
@Transactional
@RequiredArgsConstructor
public class UserFacade {

    private final UserService userService;
    public UserResponse createUser(UserSignUpRequest request) {
        User user = userService.createUser(request.toEntity());
        return UserResponse.fromEntity(user);

    }
}
