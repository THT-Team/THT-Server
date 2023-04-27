package com.tht.api.app.service;

import com.tht.api.app.entity.user.UserIdealType;
import com.tht.api.app.repository.UserIdealTypeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserIdealTypeService {

    private final UserIdealTypeRepository userIdealTypeRepository;

    public List<UserIdealType> createOf(final List<UserIdealType> entity) {
        return userIdealTypeRepository.saveAll(entity);
    }
}