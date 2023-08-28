package com.tht.api.app.repository.user;

import com.tht.api.app.entity.enums.EntityState;
import com.tht.api.app.entity.user.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>  {

    boolean existsByUsernameAndStateEquals(final String userName, final EntityState state);

    boolean existsByPhoneNumber(final String phoneNumber);

    Optional<User> findByPhoneNumber(final String phoneNumber);

    Optional<User> findByUserUuid(final String userUuid);
}
