package com.tht.api.app.scheduled;

import com.tht.api.app.entity.meta.DailyFalling;
import com.tht.api.app.entity.meta.DailyFallingActiveTimeTable;
import com.tht.api.app.repository.meta.DailyFallingActiveTimeTableRepository;
import com.tht.api.app.repository.meta.DailyFallingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.LazyBSONList;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class Macro {

    private final DailyFallingActiveTimeTableRepository timeTableRepository;
    private final DailyFallingRepository dailyFallingRepository;

    //10분마다 그날의 토픽 10분단위 매크로 데이터 삽입
    @Scheduled(cron = "0 0/10 * * * *")
    public void insertDailyFallingTalkSubjectData() {

        //insert time table
        //가장 최근 시간 조회 + 10분
        DailyFallingActiveTimeTable topTimeTable = timeTableRepository.findTopByOrderByEndDateTimeDesc();
        DailyFallingActiveTimeTable save = timeTableRepository.save(DailyFallingActiveTimeTable.of(topTimeTable.getEndDateTime(), topTimeTable.getEndDateTime().plusMinutes(10)));

        //insert daily falling
        dailyFallingRepository.saveAll(
                List.of(
                        DailyFalling.of(1, save.getIdx(), "토픽주제 매크로 1 ["+LocalDateTime.now().toString()+"]"),
                        DailyFalling.of(2, save.getIdx(), "토픽주제 매크로 2 ["+LocalDateTime.now().toString()+"]"),
                        DailyFalling.of(3, save.getIdx(), "토픽주제 매크로 3 ["+LocalDateTime.now().toString()+"]"),
                        DailyFalling.of(4, save.getIdx(), "토픽주제 매크로 4 ["+LocalDateTime.now().toString()+"]")
                )
        );
    }
}
