package com.tht.domain.administrator;

public interface AdministratorRepository {

    Administrator getAdminInfoByUuid(final String uuid);
    Administrator findByIdAndPassword(final String id, final String password);
}
