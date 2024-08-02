package com.tht.domain.entity.user.repository.querydsl;

import com.tht.domain.entity.user.mapper.UserListMapper;
import com.tht.domain.entity.user.repository.querydsl.mapper.UserDetailMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserCustomRepository  {

    Page<UserListMapper> getUserListForPage(final String search, final Pageable pageable);

    List<UserDetailMapper> getDetailInfo(final String userUuid);
}
