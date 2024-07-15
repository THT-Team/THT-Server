package com.tht.infra.user;


import com.tht.infra.user.enums.SNSType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserSnsTest {

    @Test
    @DisplayName("User SNS 생성")
    void create() {
        String user_uuid = "user uuid";
        SNSType google = SNSType.GOOGLE;
        String sns_unique_id = "sns unique id";
        String email = "email@email.com";
        UserSns userSns = UserSns.create(user_uuid, google, sns_unique_id, email);

        assertThat(userSns.getUserUuid()).isEqualTo(user_uuid);
        assertThat(userSns.getSnsUniqueId()).isEqualTo(sns_unique_id);
        assertThat(userSns.getSnsType()).isEqualTo(google);
    }
}