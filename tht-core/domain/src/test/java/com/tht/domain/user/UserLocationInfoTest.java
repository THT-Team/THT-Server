package com.tht.domain.user;

import com.tht.domain.entity.user.UserLocationInfo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserLocationInfoTest {

    private final static String USER_UUID = "test-uuid";
    private final static String ADDRESS = "주소주소";
    private final static Integer REGION_CODE = 123456789;
    private final static float LAT = 37.5F;
    private final static float LON = 127.7F;

    @Test
    @DisplayName("유저 위치정보 엔티티 생성")
    void createTest() {

        UserLocationInfo info = getInfo();

        assertThat(info.getUserUuid()).isEqualTo(USER_UUID);
    }

    private static UserLocationInfo getInfo() {
        return UserLocationInfo.create(USER_UUID, ADDRESS, REGION_CODE, LAT, LON);
    }

    private static UserLocationInfo getInfo(float lat, float lon) {
        return UserLocationInfo.create(USER_UUID, ADDRESS, REGION_CODE, lat, lon);
    }

    @Test
    @DisplayName("두 지점간의 거리 계산")
    void getDistanceTest() {

        float lat = 42.745f;
        float lon = 127.7123f;
        UserLocationInfo entity = getInfo();
        UserLocationInfo entity2 = getInfo(lat, lon);

        assertThat(entity.getDistanceBetween(lat, lon)).isEqualTo(583191);
        assertThat(entity2.getDistanceBetween(LAT, LON)).isEqualTo(583191);
    }
}