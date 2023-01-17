package com.tht.api.app.service;

import com.tht.api.app.facade.idealtype.response.IdealTypeResponse;
import com.tht.api.app.repository.meta.IdealTypeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class IdealTypeService {

    private final IdealTypeRepository idealTypeRepository;

    public List<IdealTypeResponse> findAllList() {
        return idealTypeRepository.findAllBy();
    }
}
