package com.tht.domain.entity.block.repository;

import com.tht.domain.entity.block.repository.mapper.UserBlockMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserBlockCustomRepository {

    Page<UserBlockMapper> findAllBlockList(Pageable pageable);
}
