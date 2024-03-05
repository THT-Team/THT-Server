package com.tht.api.app.service;

import com.tht.api.app.entity.meta.IdealType;
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

    public void existIn(final List<Integer> idealTypeList) {

        List<IdealType> list = idealTypeRepository.findAllByIdxIn(idealTypeList);

        if (list.size() != idealTypeList.size()) {
            throw new IllegalArgumentException(
                idealTypeList.size() + "의 이상형타입을 요청하였지만, " + list.size() + "개가 조회됩니다.");
        }
    }
}
