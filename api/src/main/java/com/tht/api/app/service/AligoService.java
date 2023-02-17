package com.tht.api.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AligoService {

    public void sendAuthNumber(final String phoneNumber, final String authNumber) {

    }
}
