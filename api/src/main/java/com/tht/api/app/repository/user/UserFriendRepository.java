package com.tht.api.app.repository.user;

import com.tht.api.app.entity.user.UserFriend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFriendRepository extends JpaRepository<UserFriend, Long> {

    int countUserFriendByUserUuid(final String userUuid);

    void deleteAllByUserUuid(final String userUuid);
}
