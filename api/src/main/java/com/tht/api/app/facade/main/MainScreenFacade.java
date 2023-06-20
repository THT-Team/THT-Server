package com.tht.api.app.facade.main;

import com.tht.api.app.facade.Facade;
import com.tht.api.app.facade.main.response.DailyFallingResponse;
import com.tht.api.app.service.DailyFallingService;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Facade
@RequiredArgsConstructor
public class MainScreenFacade {

    private final DailyFallingService dailyFallingService;
    public List<DailyFallingResponse> getDailyFallingList() {
        return dailyFallingService.findAllDailyFalling().stream()
            .map(DailyFallingResponse::of)
            .toList();
    }
}
