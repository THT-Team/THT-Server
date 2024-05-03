package com.tht.api.app.facade.main;

import com.tht.api.app.entity.meta.DailyFallingActiveInfo;
import com.tht.api.app.entity.user.User;
import com.tht.api.app.facade.Facade;
import com.tht.api.app.facade.main.response.DailyFallingResponse;
import com.tht.api.app.facade.main.response.DailyFallingTopicResponse;
import com.tht.api.app.facade.main.response.DailyTopicChooseResponse;
import com.tht.api.app.facade.main.response.TalkKeywordResponse;
import com.tht.api.app.repository.mapper.DailyFallingTimeMapper;
import com.tht.api.app.service.DailyFallingActiveService;
import com.tht.api.app.service.DailyFallingService;
import com.tht.api.app.service.TalkKeywordService;
import com.tht.api.app.service.UserDailyFallingService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Facade
@Transactional
@RequiredArgsConstructor
public class DailyFallingFacade {

    private final DailyFallingService dailyFallingService;
    private final UserDailyFallingService userDailyFallingService;
    private final DailyFallingActiveService dailyFallingActiveService;
    private final TalkKeywordService talkKeywordService;

    public DailyFallingResponse getDailyFallingList() {

        final Optional<DailyFallingActiveInfo> activeInfo = dailyFallingActiveService.findActiveInfo();

        if (activeInfo.isEmpty()) {
            return DailyFallingResponse.empty();
        }

        final List<DailyFallingTopicResponse> topicResponses = dailyFallingService.findAllDailyFalling(
                activeInfo.get().getIdx())
            .stream()
            .map(DailyFallingTopicResponse::of)
            .toList();

        return DailyFallingResponse.of(activeInfo.get().getEndDateTime(), activeInfo.get().getType(), topicResponses);
    }

    public void chooseDailyFallingKeyword(final long dailyFallingIdx, final String userUuid) {

        dailyFallingService.existByIdxAndActiveToday(dailyFallingIdx);

        userDailyFallingService.choice(dailyFallingIdx, userUuid);
    }

    public List<TalkKeywordResponse> getTalkKeywords() {
        return talkKeywordService.findAllActive()
            .stream()
            .map(TalkKeywordResponse::of)
            .toList();
    }

    public DailyTopicChooseResponse checkIsChooseDailyTopicUser(final User user) {

        Optional<DailyFallingTimeMapper> chooseTodayDailyFallingInfo = userDailyFallingService.findChooseTodayDailyFallingInfo(user.getUserUuid());

        if (chooseTodayDailyFallingInfo.isPresent()) {
            return DailyTopicChooseResponse.isChooseDone();
        }
        return DailyTopicChooseResponse.isNotChoose();
    }
}
