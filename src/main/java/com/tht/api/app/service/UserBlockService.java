package com.tht.api.app.service;

import com.tht.api.app.entity.user.UserBlock;
import com.tht.api.app.repository.user.UserBlockRepository;
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
