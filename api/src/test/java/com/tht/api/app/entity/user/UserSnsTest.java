package com.tht.api.app.entity.user;


import static org.assertj.core.api.Assertions.assertThat;

import com.tht.api.app.entity.enums.SNSType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserSnsTest {

    @Test
    @DisplayName("User SNS 생성")
    void create() {
        String user_uuid = "user uuid";
        SNSType google = SNSType.GOOGLE;
        String sns_unique_id = "sns unique id";
        UserSns userSns = UserSns.create(user_uuid, google, sns_unique_id);

        assertThat(userSns.getUserUuid()).isEqualTo(user_uuid);
        assertThat(userSns.getSnsUniqueId()).isEqualTo(sns_unique_id);
        assertThat(userSns.getSnsType()).isEqualTo(google);
    }
}