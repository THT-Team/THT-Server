package com.tht.domain.entity.user.repository;

import com.tht.domain.entity.user.UserFriend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserFriendRepository extends JpaRepository<UserFriend, Long> {

    int countUserFriendByUserUuid(final String userUuid);

    List<UserFriend> findAllByUserUuid(final String userUuid);
}
