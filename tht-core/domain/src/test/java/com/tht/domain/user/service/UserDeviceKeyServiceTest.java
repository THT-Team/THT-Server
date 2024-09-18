package com.tht.domain.user.service;

import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.anyString;
import static org.mockito.BDDMockito.times;
import static org.mockito.BDDMockito.verify;
import static org.mockito.BDDMockito.when;
import static org.mockito.Mockito.mock;

import com.tht.domain.entity.user.UserDeviceKey;
import com.tht.domain.entity.user.repository.UserDeviceKeyRepository;
import com.tht.domain.entity.user.service.UserDeviceKeyService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserDeviceKeyServiceTest {

    @Mock
    UserDeviceKeyRepository userDeviceKeyRepository;

    @InjectMocks
    UserDeviceKeyService deviceKeyService;

    @Test
    @DisplayName("유저 uuid 와 매핑되는 deviceKey 가 존재하지 않음 (생성)")
    void updateByUserUuidAndDeviceKey() {
        when(userDeviceKeyRepository.findByUserUuidAndDeviceKey(anyString(),
            anyString())).thenReturn(Optional.empty());

        deviceKeyService.update(anyString(), anyString());

        verify(userDeviceKeyRepository).save(any());
    }

    @Test
    @DisplayName("유저 uuid 와 매핑되는 deviceKey 가 존재 (미생성)")
    void nonUpdateByUserUuidAndDeviceKey() {
        when(userDeviceKeyRepository.findByUserUuidAndDeviceKey(anyString(),
            anyString())).thenReturn(Optional.of(mock(UserDeviceKey.class)));

        deviceKeyService.update(anyString(), anyString());

        verify(userDeviceKeyRepository, times(0)).save(any());
    }
}