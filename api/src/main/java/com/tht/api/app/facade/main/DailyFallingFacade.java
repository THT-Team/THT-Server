package com.tht.api.app.facade.main;

import com.tht.api.app.facade.Facade;
import com.tht.api.app.facade.main.response.DailyFallingResponse;
import com.tht.api.app.service.DailyFallingService;
import com.tht.api.app.service.UserDailyFallingService;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Facade
@RequiredArgsConstructor
public class DailyFallingFacade {

    private final DailyFallingService dailyFallingService;
    private final UserDailyFallingService userDailyFallingService;

    public List<DailyFallingResponse> getDailyFallingList() {
        return dailyFallingService.findAllDailyFalling().stream()
            .map(DailyFallingResponse::of)
            .toList();
    }

    public void chooseDailyFallingKeyword(final long dailyFallingIdx, final String userUuid) {

        dailyFallingService.existByIdxAndActiveToday(dailyFallingIdx);

        userDailyFallingService.insert(dailyFallingIdx, userUuid);
    }
}
