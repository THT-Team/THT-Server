package com.tht.thtapis.service;

import com.tht.infra.idealtype.IdealType;
import com.tht.infra.idealtype.IdealTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class IdealTypeService {

    private final IdealTypeRepository idealTypeRepository;

    public List<IdealType> findAllList() {
        return idealTypeRepository.findAll();
    }

    public void existIn(final List<Integer> idealTypeList) {

        List<IdealType> list = idealTypeRepository.findAllByIdxIn(idealTypeList);

        if (list.size() != idealTypeList.size()) {
            throw new IllegalArgumentException(
                idealTypeList.size() + "의 이상형타입을 요청하였지만, " + list.size() + "개가 조회됩니다.");
        }
    }
}
