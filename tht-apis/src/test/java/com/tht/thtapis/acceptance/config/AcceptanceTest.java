package com.tht.thtapis.acceptance.config;

import com.tht.infra.dailyfalling.DailyFalling;
import com.tht.infra.dailyfalling.DailyFallingActiveInfo;
import com.tht.enums.dailyfalling.DailyFallingType;
import com.tht.infra.dailyfalling.repository.DailyFallingActiveTimeTableRepository;
import com.tht.infra.dailyfalling.repository.DailyFallingRepository;
import com.tht.infra.idealtype.IdealType;
import com.tht.infra.idealtype.IdealTypeRepository;
import com.tht.infra.interesst.Interest;
import com.tht.infra.interesst.InterestRepository;
import com.tht.infra.talkkeyword.TalkKeyword;
import com.tht.infra.talkkeyword.TalkKeywordRepository;
import com.tht.thtapis.security.TokenProvider;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

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
        for (int i = 0; i < 3; i++) {
            interestRepository.save(Interest.of("관심사" + i, ""));
            idealTypeRepository.save(IdealType.of("이상형" + i, ""));
        }
    }

    public DailyFalling 그날의주제어_생성_요청() {

        final LocalDateTime now = LocalDateTime.now();

        final TalkKeyword talkKeyword = talkKeywordRepository.save(TalkKeyword.of(1, "주제어"));

        final DailyFallingActiveInfo timeTable = dailyFallingActiveTimeTableRepository.save(
            DailyFallingActiveInfo.of(now.minusDays(1), now.plusDays(1), DailyFallingType.ONE_CHOICE, "오늘 나는 너랑.."));

        return dailyFallingRepository.save(
            DailyFalling.of(talkKeyword.getIdx(), timeTable.getIdx(), "잡담내용~~ 잡담~")
        );
    }

    public String getUserUuid(String accessToken) {
        return tokenProvider.getAuthentication(accessToken).getCredentials().toString();
    }
}
