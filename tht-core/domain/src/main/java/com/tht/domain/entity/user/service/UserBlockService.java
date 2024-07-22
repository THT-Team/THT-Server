package com.tht.domain.entity.user.service;

import com.tht.domain.entity.user.UserBlock;
import com.tht.domain.entity.user.repository.UserBlockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserBlockService {

    private final UserBlockRepository repository;

    public void block(final String userUuid, final String reportUserUuid) {

        if (repository.existsByUserUuidAndBlockUserUuid(userUuid, reportUserUuid)) {
            return;
        }
        repository.save(UserBlock.create(userUuid, reportUserUuid));
    }
}
