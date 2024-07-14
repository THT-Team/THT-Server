package com.tht.thtapis.ui;

import java.util.List;

import com.tht.thtapis.facade.interest.InterestFacade;
import com.tht.thtapis.facade.interest.response.InterestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/interests")
public class InterestController {

    private final InterestFacade interestFacade;

    @GetMapping
    public ResponseEntity<List<InterestResponse>> getInterestList() {
        return ResponseEntity.ok(interestFacade.getInterestList());
    }
}
