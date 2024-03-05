package com.tht.api.app.unit.entity.user;

import static org.assertj.core.api.Assertions.assertThat;

import com.tht.api.app.entity.user.UserLocationInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserLocationInfoTest {

    private final static String userUuid = "test-uuid";
    private final static String address = "주소주소";
    private final static Integer regionCode = 123456789;
    private final static float lat = 37.5F;
    private final static float lon = 127.7F;

    @Test
    @DisplayName("유저 위치정보 엔티티 생성")
    void createTest() {

        UserLocationInfo info = UserLocationInfo.create(userUuid, address, regionCode, lat, lon);

        assertThat(info.getUserUuid()).isEqualTo(userUuid);
    }
}