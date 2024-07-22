package com.tht.thtapis.facade.interest;

import com.tht.thtapis.facade.Facade;
import com.tht.thtapis.facade.interest.response.InterestResponse;
import java.util.List;

import com.tht.domain.entity.interesst.InterestService;
import lombok.RequiredArgsConstructor;

@Facade
@RequiredArgsConstructor
public class InterestFacade {

    private final InterestService interestService;

    public List<InterestResponse> getInterestList() {
        return interestService.findAllList()
            .stream()
            .map(InterestResponse::of)
            .toList();
    }
}
