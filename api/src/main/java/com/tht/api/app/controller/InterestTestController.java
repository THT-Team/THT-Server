package com.tht.api.app.controller;


import com.tht.api.app.facade.interestTest.InterestTestFacade;
import com.tht.api.app.facade.interestTest.response.InterestTestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/interestTest")
public class InterestTestController {
    private final InterestTestFacade interestTestFacade;
//test
    @GetMapping
    public ResponseEntity<List<InterestTestResponse>> getInterestTestList(){
        return ResponseEntity.ok(interestTestFacade.getInterestTestList());
    }
}
