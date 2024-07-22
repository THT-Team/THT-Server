package com.tht.domain.entity.administrator;

import com.tht.domain.exception.EntityStateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdministratorService {

    private final AdministratorRepository repository;

    public Administrator getAdministratorByName(final String name) {
        return repository.findByUsername(name)
            .orElseThrow(
                () -> EntityStateException.doNotExistOf("administrator : " + name)
            );
    }

    public Administrator getLoginInfo(final String id, final String password) {
        return repository.findByIdAndPassword(id, password).orElseThrow(
            () -> EntityStateException.doNotExistOf(String.format("administrator : id(%s), password(%s)",id, password)
            ));
    }

    public Administrator createAdmin(final String id, final String password, final String userName) {

        if (repository.findByUsername(userName).isPresent()) {
            throw EntityStateException.duplicateColumnOf("admin", userName);
        }

        if (repository.findById(id).isPresent()) {
            throw EntityStateException.duplicateColumnOf("admin", id);
        }

        return repository.save(new Administrator(id, password, userName));
    }
}
