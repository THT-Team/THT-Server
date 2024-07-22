package com.tht.domain.administrator;

import com.tht.enums.user.UserRole;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Administrator {

    private final String id;
    private final String password;
    private final String username;
    private final String userUuid;
    private final UserRole role;

}
