package com.tht.api.app.controller;

import com.tht.api.app.entity.user.User;
import com.tht.api.app.facade.main.DailyFallingFacade;
import com.tht.api.app.facade.main.response.TalkKeywordResponse;
import com.tht.api.app.facade.main.response.DailyFallingResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DailyFallingController {

    private final DailyFallingFacade dailyFallingFacade;

    @GetMapping("/falling/daily-keyword")
    public ResponseEntity<DailyFallingResponse> getDailyFallingList() {

        return ResponseEntity.ok(dailyFallingFacade.getDailyFallingList());
    }

    @PostMapping("/falling/choice/daily-keyword/{daily-falling-idx}")
    public ResponseEntity<Object> chooseFallingKeyword(
        @PathVariable(name = "daily-falling-idx") final long dailyFallingIdx,
        @AuthenticationPrincipal User user
    ) {

        dailyFallingFacade.chooseDailyFallingKeyword(dailyFallingIdx, user.getUserUuid());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all/talk-keyword")
    public ResponseEntity<List<TalkKeywordResponse>> getTalkKeyword() {

        return ResponseEntity.ok(dailyFallingFacade.getTalkKeywords());
    }
}
