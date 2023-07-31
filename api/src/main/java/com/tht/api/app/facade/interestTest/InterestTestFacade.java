package com.tht.api.app.facade.interestTest;

import com.tht.api.app.facade.Facade;
import com.tht.api.app.facade.interestTest.response.InterestTestResponse;
import com.tht.api.app.service.InterestTestService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Facade
@RequiredArgsConstructor
public class InterestTestFacade {

    private final InterestTestService interestTestService;

    public List<InterestTestResponse> getInterestTestList(){
        return interestTestService.findAllList();
    }
}
