package com.tht.domain.entity.user.repository.querydsl;

import com.tht.domain.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserCustomRepository  {

    Page<User> getUserListForPage(final String search, final Pageable pageable);
}
