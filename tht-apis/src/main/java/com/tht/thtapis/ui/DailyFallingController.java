package com.tht.thtapis.ui;

import com.tht.infra.user.User;
import com.tht.thtapis.facade.main.DailyFallingFacade;
import com.tht.thtapis.facade.main.response.DailyFallingResponse;
import com.tht.thtapis.facade.main.response.DailyTopicChooseResponse;
import com.tht.thtapis.facade.main.response.TalkKeywordResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("/check/is-choose-daily-topic")
    public ResponseEntity<DailyTopicChooseResponse> isChooseDailyTopic(@AuthenticationPrincipal User user) {

        return ResponseEntity.ok(dailyFallingFacade.checkIsChooseDailyTopicUser(user));
    }
}
