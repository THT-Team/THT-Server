package com.tht.domain.entity.administrator;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {

    Optional<Administrator> findByUsername(final String userName);

    Optional<Administrator> findByIdAndPassword(final String id, final String password);
}
