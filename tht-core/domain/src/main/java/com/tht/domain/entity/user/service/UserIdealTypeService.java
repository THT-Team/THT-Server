package com.tht.domain.entity.user.service;

import java.util.List;

import com.tht.domain.entity.idealtype.IdealTypeMapper;
import com.tht.domain.entity.user.UserIdealType;
import com.tht.domain.entity.user.repository.UserIdealTypeRepository;
import com.tht.thtcommonutils.utils.LogWriteUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserIdealTypeService {

    private final UserIdealTypeRepository userIdealTypeRepository;

    public List<UserIdealType> createOf(final List<UserIdealType> entity) {
        LogWriteUtils.logInfo("new_user_ideal_type : " + entity);

        return userIdealTypeRepository.saveAll(entity);
    }

    public List<IdealTypeMapper> findBy(final String userUuid) {
        return userIdealTypeRepository.findIdealInfoBy(userUuid);
    }

    public void update(final String userUuid, final List<Integer> idealTypeList) {

        userIdealTypeRepository.deleteAllByUserUuid(userUuid);
        userIdealTypeRepository.saveAll(
            idealTypeList.stream()
                .map(idx -> UserIdealType.create(userUuid, idx))
                .toList()
        );
    }
}
