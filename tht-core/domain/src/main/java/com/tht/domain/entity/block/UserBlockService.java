package com.tht.domain.entity.block;

import com.tht.domain.entity.block.dto.UserBlockDto;
import com.tht.domain.entity.block.repository.UserBlockRepository;
import com.tht.domain.entity.block.repository.mapper.UserBlockMapper;
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

        final Page<UserBlockMapper> blockList = repository.findAllBlockList(pageable);
        final List<UserBlockDto> contents = blockList.getContent().stream().map(UserBlockDto::ofMapper).toList();

        return new PageImpl<>(contents, pageable, blockList.getTotalElements());
    }
}