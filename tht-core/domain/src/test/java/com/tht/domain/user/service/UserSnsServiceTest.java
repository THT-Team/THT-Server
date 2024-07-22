package com.tht.domain.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.when;

import java.util.List;
import java.util.Optional;

import com.tht.domain.entity.user.service.UserSnsService;
import com.tht.domain.exception.EntityStateException;
import com.tht.enums.user.SNSType;
import com.tht.domain.entity.user.exception.UserCustomException;
import com.tht.domain.entity.user.repository.UserSnsRepository;
import fixture.user.UserSnsMapperFixture;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserSnsServiceTest {

    @Mock
    UserSnsRepository userSnsRepository;

    @InjectMocks
    UserSnsService userSnsService;

    @Test
    @DisplayName("SNS 고유 ID 로 가입된 이력이 있는지 판별")
    void duplicateSnsId() {
        when(userSnsRepository.existsBySnsTypeAndSnsUniqueId(any(), anyString())).thenReturn(true);

        SNSType normal = SNSType.NORMAL;
        String snsUniqueId = "snsUniqueId";
        String email = "email@email.com";

        assertThatThrownBy(
            () -> userSnsService.create("user-uuid", normal, snsUniqueId, email))
            .isInstanceOf(EntityStateException.class)
            .hasMessageContaining("해당 " + normal + " 계정이 이미 존재합니다.");
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    @DisplayName("sns 중복가입 여부가 존재하면 true, 없으면 false")
    void isExistIntegratedUserInfo(final Boolean result) {

        when(userSnsRepository.existsByUserUuidAndSnsTypeOrSnsTypeAndSnsUniqueId(anyString(), any(),
            any(), anyString())).thenReturn(result);

        assertThat(userSnsService.isExistIntegratedUserInfo("", SNSType.NORMAL, ""))
            .isEqualTo(result);
    }

    @Test
    @DisplayName("회원 번호로 가입된 sns 계정 타입 리스트 조회 - SNS Type List 반환")
    void findAllByPhoneNumber_all() {
        SNSType[] values = SNSType.values();

        when(userSnsRepository.findAllByPhoneNumber(anyString()))
            .thenReturn(Optional.of(List.of(
                UserSnsMapperFixture.ofType(values[0]),
                UserSnsMapperFixture.ofType(values[1]),
                UserSnsMapperFixture.ofType(values[2]),
                UserSnsMapperFixture.ofType(values[3])
            )));

        assertThat(userSnsService.findAllByPhoneNumber("DAsf"))
            .contains(values);
    }

    @Test
    @DisplayName("가입한 snsType 과 고유 아이디로 유저 uuid 찾기 (성공)")
    void findUserUuidBySnsIdKey() {

        String value = "yes";

        when(userSnsRepository.findUserUuidBySnsIdKey(any(), anyString())).thenReturn(
            Optional.of(value));

        assertThat(userSnsService.findUserUuidBySnsIdKey(SNSType.NORMAL, ""))
            .isEqualTo(value);
    }

    @Test
    @DisplayName("가입한 snsType 과 고유 아이디로 유저 uuid 찾기 (싪패)")
    void findUserUuidBySnsIdKey_fail() {


        when(userSnsRepository.findUserUuidBySnsIdKey(any(), anyString())).thenReturn(
            Optional.empty());

        assertThatThrownBy(() -> userSnsService.findUserUuidBySnsIdKey(SNSType.NORMAL, ""))
            .isInstanceOf(UserCustomException.class)
            .hasMessageContaining("해당 SNS ID가 존재하지 않습니다.");
    }
}