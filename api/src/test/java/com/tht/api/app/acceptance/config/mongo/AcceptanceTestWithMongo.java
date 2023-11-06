package com.tht.api.app.acceptance.config.mongo;

import com.tht.api.app.acceptance.config.AcceptanceTest;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

@EnableMongoTestServer
public abstract class AcceptanceTestWithMongo extends AcceptanceTest {

    @Autowired
    MongoTemplate mongoTemplate;

    @BeforeEach
    public void mongoCleanUp() {
        mongoTemplate.getDb().drop();
    }

}
