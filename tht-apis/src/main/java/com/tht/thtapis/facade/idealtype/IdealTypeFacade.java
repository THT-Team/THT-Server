package com.tht.thtapis.facade.idealtype;

import com.tht.thtapis.facade.Facade;
import com.tht.thtapis.facade.idealtype.response.IdealTypeResponse;
import java.util.List;

import com.tht.domain.entity.idealtype.IdealTypeService;
import lombok.RequiredArgsConstructor;

@Facade
@RequiredArgsConstructor
public class IdealTypeFacade {

    private final IdealTypeService idealTypeService;

    public List<IdealTypeResponse> getIdealTypeList() {
        return idealTypeService.findAllList()
            .stream().map(IdealTypeResponse::of)
            .toList();
    }
}
