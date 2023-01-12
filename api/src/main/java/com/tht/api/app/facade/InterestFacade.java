package com.tht.api.app.facade;

import com.tht.api.app.facade.response.InterestResponse;
import com.tht.api.app.service.InterestService;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Facade
@RequiredArgsConstructor
public class InterestFacade {

    private final InterestService interestService;

    public List<InterestResponse> getInterestList() {
        return interestService.findAllList();
    }
}
