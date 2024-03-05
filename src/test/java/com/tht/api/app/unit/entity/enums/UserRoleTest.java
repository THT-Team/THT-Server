package com.tht.api.app.unit.entity.enums;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.tht.api.app.entity.enums.UserRole;
import com.tht.api.exception.custom.EnumStateNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class UserRoleTest {

    @ParameterizedTest
    @ValueSource(strings = {"ADMIN", "NORMAL"})
    @DisplayName("String to Enum Convert (성공)")
    void toConverter_success(final String name) {

        UserRole userRole = UserRole.valueOf(name);
        assertThat(UserRole.toConverter(name)).isEqualTo(userRole);
    }

    @Test
    @DisplayName("String to Enum Convert (실패)")
    void toConverter_fail() {

        String name = "없는 UserRole";
        assertThatThrownBy(() -> UserRole.toConverter(name))
            .isInstanceOf(EnumStateNotFoundException.class)
            .hasMessageContaining(String.format("유저 권한 목록에 %s가 존재하지 않습니다.", name));
    }
}