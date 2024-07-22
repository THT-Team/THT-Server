package com.tht.thtadmin.acceptance.config;

import com.tht.domain.entity.dailyfalling.repository.DailyFallingActiveTimeTableRepository;
import com.tht.domain.entity.dailyfalling.repository.DailyFallingRepository;
import com.tht.domain.entity.idealtype.IdealTypeRepository;
import com.tht.domain.entity.interesst.InterestRepository;
import com.tht.domain.entity.talkkeyword.TalkKeywordRepository;
import io.restassured.RestAssured;
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

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
        databaseCleanup.execute();
    }

}
