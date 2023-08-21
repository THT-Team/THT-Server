package com.tht.api.app.repository.user;

import com.tht.api.app.entity.user.UserFriend;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFriendRepository extends JpaRepository<UserFriend, Long> {

    int countUserFriendByUserUuid(final String userUuid);

    List<UserFriend> findAllByUserUuid(final String userUuid);
}
