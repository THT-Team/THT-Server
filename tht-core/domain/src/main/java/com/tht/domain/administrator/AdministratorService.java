package com.tht.domain.administrator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdministratorService {

    private final AdministratorRepository repository;

    public Administrator getAdministratorByUuid(final String uuid) {
        return repository.getAdminInfoByUuid(uuid);
    }

    public Administrator getLoginInfo(final String id, final String password) {
        return repository.findByIdAndPassword(id, password);
    }
}
