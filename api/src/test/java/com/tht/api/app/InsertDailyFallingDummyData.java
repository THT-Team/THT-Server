package com.tht.api.app;

import com.tht.api.app.entity.meta.DailyFalling;
import com.tht.api.app.entity.meta.DailyFallingActiveTimeTable;
import com.tht.api.app.repository.meta.DailyFallingActiveTimeTableRepository;
import com.tht.api.app.repository.meta.DailyFallingRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class InsertDailyFallingDummyData {

    @Autowired
    DailyFallingActiveTimeTableRepository timeTableRepository;

    @Autowired
    DailyFallingRepository dailyFallingRepository;

    /**
     * 그날의 토픽 더미 테스트 데이터 삽입 10분단위
     */
    @Test
    public void insertDailyFallingTalkSubjectData() {

        //insert time table
        //가장 최근 시간 조회 + 10분
        DailyFallingActiveTimeTable topTimeTable = timeTableRepository.findTopByOrderByEndDateTimeDesc();
        DailyFallingActiveTimeTable save = timeTableRepository.save(DailyFallingActiveTimeTable.of(topTimeTable.getEndDateTime(), topTimeTable.getEndDateTime().plusMinutes(10)));

        //insert daily falling
        dailyFallingRepository.saveAll(
                List.of(
                        DailyFalling.of(1, save.getIdx(), "토픽주제 매크로 1 ["+ LocalDateTime.now()+"]"),
                        DailyFalling.of(2, save.getIdx(), "토픽주제 매크로 2 ["+LocalDateTime.now().toString()+"]"),
                        DailyFalling.of(3, save.getIdx(), "토픽주제 매크로 3 ["+LocalDateTime.now().toString()+"]"),
                        DailyFalling.of(4, save.getIdx(), "토픽주제 매크로 4 ["+LocalDateTime.now().toString()+"]")
                )
        );
    }
}