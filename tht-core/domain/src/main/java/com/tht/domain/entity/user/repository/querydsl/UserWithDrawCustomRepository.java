package com.tht.domain.entity.user.repository.querydsl;

import com.tht.domain.entity.user.repository.querydsl.mapper.UserWithDrawLogMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserWithDrawCustomRepository {
    Page<UserWithDrawLogMapper> findAllWithDrawLogList(final Pageable pageable);
}
