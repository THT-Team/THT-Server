package com.tht.thtadmin.ui.user;

import com.tht.domain.entity.user.User;
import com.tht.domain.entity.user.service.UserService;
import com.tht.thtadmin.ui.user.response.UserSimpleListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserManageUseCase {

    private final UserService userService;

    public Page<UserSimpleListResponse> getUserList(final String search, final Pageable pageable) {

        final Page<User> pageResult = userService.getSimpleUserPageList(search, pageable);

        final List<UserSimpleListResponse> responses = pageResult
            .getContent()
            .stream()
            .map(user -> UserSimpleListResponse.of(
                user.getUsername(),
                user.getCreatedAt(),
                user.getState())
            )
            .toList();

        return new PageImpl<>(responses, pageable, pageResult.getTotalElements());
    }
}
