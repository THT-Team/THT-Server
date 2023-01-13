package com.tht.api.app.facade.idealtype;

import com.tht.api.app.facade.Facade;
import com.tht.api.app.facade.idealtype.response.IdealTypeResponse;
import com.tht.api.app.service.IdealTypeService;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Facade
@RequiredArgsConstructor
public class IdealTypeFacade {

    private final IdealTypeService idealTypeService;

    public List<IdealTypeResponse> getIdealTypeList() {
        return idealTypeService.findAllList();
    }
}
