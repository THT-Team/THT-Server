package com.tht.api.app.service;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.times;
import static org.mockito.BDDMockito.verify;
import static org.mockito.BDDMockito.when;

import com.tht.api.app.repository.UserDeviceKeyRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserDeviceKeyServiceTest {

    @Mock
    UserDeviceKeyRepository userDeviceKeyRepository;

    @InjectMocks
    UserDeviceKeyService deviceKeyService;

    @Test
    @DisplayName("유저 uuid 와 매핑되는 deviceKey 가 존재하지 않음 (생성)")
    void createByUserUuidAndDeviceKey() {
        when(userDeviceKeyRepository.existsByUserUuidAndDeviceKey(anyString(),
            anyString())).thenReturn(false);

        deviceKeyService.create(anyString(), anyString());

        verify(userDeviceKeyRepository).save(any());
    }

    @Test
    @DisplayName("유저 uuid 와 매핑되는 deviceKey 가 존재 (미생성)")
    void nonCreateByUserUuidAndDeviceKey() {
        when(userDeviceKeyRepository.existsByUserUuidAndDeviceKey(anyString(),
            anyString())).thenReturn(true);

        deviceKeyService.create(anyString(), anyString());

        verify(userDeviceKeyRepository, times(0)).save(any());
    }
}