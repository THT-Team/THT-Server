package com.tht.api.app.controller;

import com.tht.api.app.facade.main.MainScreenFacade;
import com.tht.api.app.facade.main.response.DailyFallingResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainScreenController {

    private final MainScreenFacade mainScreenFacade;

    @GetMapping("/falling/daily-keyword")
    public ResponseEntity<List<DailyFallingResponse>> getDailyFallingList() {

        return ResponseEntity.ok(mainScreenFacade.getDailyFallingList());
    }
}
