package com.tht.thtapis.ui;

import java.util.List;

import com.tht.thtapis.facade.idealtype.IdealTypeFacade;
import com.tht.thtapis.facade.idealtype.response.IdealTypeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ideal-types")
public class IdealTypeController {

    private final IdealTypeFacade idealTypeFacade;

    @GetMapping
    public ResponseEntity<List<IdealTypeResponse>> getInterestList() {
        return ResponseEntity.ok(idealTypeFacade.getIdealTypeList());
    }
}
