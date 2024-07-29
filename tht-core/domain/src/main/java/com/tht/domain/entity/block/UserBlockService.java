package com.tht.domain.entity.block;

import com.tht.domain.entity.block.dto.UserBlockDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserBlockService {

    private final UserBlockRepository repository;

    @Transactional
    public void block(final String userUuid, final String reportUserUuid) {

        if (repository.existsByUserUuidAndBlockUserUuid(userUuid, reportUserUuid)) {
            return;
        }
        repository.save(UserBlock.create(userUuid, reportUserUuid));
    }

    public Page<UserBlockDto> getBlockList(final Pageable pageable) {

        return null;
    }
}