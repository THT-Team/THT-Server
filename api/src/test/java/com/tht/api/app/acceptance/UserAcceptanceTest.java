package com.tht.api.app.acceptance;

import static com.tht.api.app.acceptance.UserAcceptanceStep.그날의_대화토픽_선택_요청;
import static com.tht.api.app.acceptance.UserAcceptanceStep.메인화면_조회_요청;
import static com.tht.api.app.acceptance.UserAcceptanceStep.신규유저_생성_요청_후_토큰추출;
import static com.tht.api.app.acceptance.UserAcceptanceStep.유저계정_탈퇴_요청;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.tht.api.app.acceptance.config.AcceptanceTest;
import com.tht.api.app.entity.meta.DailyFalling;
import com.tht.api.app.entity.meta.DailyFallingActiveTimeTable;
import com.tht.api.app.entity.meta.IdealType;
import com.tht.api.app.entity.meta.Interest;
import com.tht.api.app.repository.meta.DailyFallingActiveTimeTableRepository;
import com.tht.api.app.repository.meta.DailyFallingRepository;
import com.tht.api.app.repository.meta.IdealTypeRepository;
import com.tht.api.app.repository.meta.InterestRepository;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class UserAcceptanceTest extends AcceptanceTest {

    @Autowired
    DailyFallingRepository dailyFallingRepository;

    @Autowired
    DailyFallingActiveTimeTableRepository dailyFallingActiveTimeTableRepository;

    @Autowired
    InterestRepository interestRepository;

    @Autowired
    IdealTypeRepository idealTypeRepository;

    @DisplayName("사용자 탈퇴")
    @Test
    void 사용자탈퇴() {

        //given
        var dailyFalling = 그날의주제어_생성_요청();

        for(int i=0; i<3; i++){
            interestRepository.save(Interest.of("관심사"+i,""));
            idealTypeRepository.save(IdealType.of("이상형"+i, ""));
        }

        //given
        var 탈퇴할_사용자 = 신규유저_생성_요청_후_토큰추출("삭제할 유저", "01012341234");
        var 일반_사용자1 = 신규유저_생성_요청_후_토큰추출("일반 사용자1", "01065689787");
        var 일반_사용자2 = 신규유저_생성_요청_후_토큰추출("일반 사용자2", "01065689737");

        그날의_대화토픽_선택_요청(dailyFalling.getIdx(), 탈퇴할_사용자);
        그날의_대화토픽_선택_요청(dailyFalling.getIdx(), 일반_사용자1);
        그날의_대화토픽_선택_요청(dailyFalling.getIdx(), 일반_사용자2);

        //when
        var 삭제요청결과 = 유저계정_탈퇴_요청(탈퇴할_사용자);
        var 메인화면조회결과 = 메인화면_조회_요청(일반_사용자1);

        //then
        assertAll(
            () -> assertThat(삭제요청결과.statusCode()).isEqualTo(204),
            () -> assertThat(메인화면조회결과.jsonPath().getList("userInfos.username")).isNotEmpty(),
            () -> assertThat(메인화면조회결과.jsonPath().getList("userInfos.username")).doesNotContain("삭제할 유저")
        );

    }

    private DailyFalling 그날의주제어_생성_요청() {
        LocalDateTime now = LocalDateTime.now();

        DailyFallingActiveTimeTable timeTable = dailyFallingActiveTimeTableRepository.save(
            DailyFallingActiveTimeTable.of(now.minusDays(1), now.plusDays(1)));

        return dailyFallingRepository.save(DailyFalling.of(1, timeTable.getIdx(), "주제어"));
    }

}
