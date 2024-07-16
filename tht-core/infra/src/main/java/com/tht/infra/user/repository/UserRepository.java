package com.tht.infra.user.repository;

import com.tht.infra.EntityState;
import com.tht.infra.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>  {

    boolean existsByUsernameAndStateEquals(final String userName, final EntityState state);

    boolean existsByPhoneNumber(final String phoneNumber);
}
