package com.tht.domain.user;

import com.tht.infra.user.UserDeviceKey;
import com.tht.infra.user.repository.UserDeviceKeyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
class UserDeviceKeyServiceTest {

    @Mock
    UserDeviceKeyRepository userDeviceKeyRepository;

    @InjectMocks
    UserDeviceKeyService deviceKeyService;

    @Test
    @DisplayName("유저 uuid 와 매핑되는 deviceKey 가 존재하지 않음 (생성)")
    void recordDeviceKeyByUserUuidAndDeviceKey() {

        UserDeviceKey mock = Mockito.mock(UserDeviceKey.class);
        doNothing().when(mock).changeKey(anyString());

        Optional<UserDeviceKey> mockUserDeviceKey = Optional.of(mock);
        when(userDeviceKeyRepository.findByUserUuid(anyString())).thenReturn(mockUserDeviceKey);

        //when
        deviceKeyService.recordDeviceKey("user-uuid", "device-key");

        //then
        verify(userDeviceKeyRepository, times(0)).save(any());
    }

    @Test
    @DisplayName("유저 uuid 와 매핑되는 deviceKey 가 존재 (변경)")
    void nonRecordDeviceKeyByUserUuidAndDeviceKey() {

        when(userDeviceKeyRepository.findByUserUuid(anyString())).thenReturn(Optional.empty());
        deviceKeyService.recordDeviceKey("user-uuid", "device-key");

        verify(userDeviceKeyRepository).save(any());
    }
}