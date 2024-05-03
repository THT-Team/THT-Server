package com.tht.api.app.acceptance.config;

import com.tht.api.app.config.security.TokenProvider;
import com.tht.api.app.entity.enums.DailyFallingType;
import com.tht.api.app.entity.meta.DailyFalling;
import com.tht.api.app.entity.meta.DailyFallingActiveTimeTable;
import com.tht.api.app.entity.meta.IdealType;
import com.tht.api.app.entity.meta.Interest;
import com.tht.api.app.entity.meta.TalkKeyword;
import com.tht.api.app.repository.meta.DailyFallingActiveTimeTableRepository;
import com.tht.api.app.repository.meta.DailyFallingRepository;
import com.tht.api.app.repository.meta.IdealTypeRepository;
import com.tht.api.app.repository.meta.InterestRepository;
import com.tht.api.app.repository.meta.TalkKeywordRepository;
import io.restassured.RestAssured;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public abstract class AcceptanceTest {

    @Autowired
    private DatabaseCleanup databaseCleanup;

    @Autowired
    DailyFallingRepository dailyFallingRepository;

    @Autowired
    DailyFallingActiveTimeTableRepository dailyFallingActiveTimeTableRepository;

    @Autowired
    InterestRepository interestRepository;

    @Autowired
    IdealTypeRepository idealTypeRepository;

    @Autowired
    TalkKeywordRepository talkKeywordRepository;

    @Autowired
    TokenProvider tokenProvider;

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        databaseCleanup.execute();

        setUpCreateUser();
    }

    private void setUpCreateUser() {
        for(int i=0; i<3; i++){
            interestRepository.save(Interest.of("관심사"+i,""));
            idealTypeRepository.save(IdealType.of("이상형"+i, ""));
        }
    }

    public DailyFalling 그날의주제어_생성_요청() {

        final LocalDateTime now = LocalDateTime.now();

        final TalkKeyword talkKeyword = talkKeywordRepository.save(TalkKeyword.of(1, "주제어"));

        final DailyFallingActiveTimeTable timeTable = dailyFallingActiveTimeTableRepository.save(
            DailyFallingActiveTimeTable.of(now.minusDays(1), now.plusDays(1)));

        return dailyFallingRepository.save(
            DailyFalling.of(talkKeyword.getIdx(), timeTable.getIdx(), "잡담내용~~ 잡담~", DailyFallingType.ONE_CHOICE)
        );
    }

    public String getUserUuid(String accessToken) {
        return tokenProvider.getAuthentication(accessToken).getCredentials().toString();
    }
}
