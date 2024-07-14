package com.tht.thtapis.facade.main;

import com.tht.infra.dailyfalling.DailyFallingActiveInfo;
import com.tht.infra.dailyfalling.mapper.DailyFallingTimeMapper;
import com.tht.infra.user.User;
import com.tht.thtapis.facade.Facade;
import com.tht.thtapis.facade.main.response.DailyFallingResponse;
import com.tht.thtapis.facade.main.response.DailyFallingTopicResponse;
import com.tht.thtapis.facade.main.response.DailyTopicChooseResponse;
import com.tht.thtapis.facade.main.response.TalkKeywordResponse;
import com.tht.thtapis.service.DailyFallingActiveService;
import com.tht.thtapis.service.DailyFallingService;
import com.tht.thtapis.service.TalkKeywordService;
import com.tht.thtapis.service.UserDailyFallingService;
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

        return DailyFallingResponse.of(activeInfo.get().getEndDateTime(), activeInfo.get().getType(), activeInfo.get().getIntroduction(), topicResponses);
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
