package com.tht.api.app.service;

import com.tht.api.app.entity.user.UserFriend;
import com.tht.api.app.facade.user.request.ContactDto;
import com.tht.api.app.repository.user.UserFriendRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserFriendService {

    private final UserFriendRepository userFriendRepository;

    public int getFriendCount(final String userUuid) {
        return userFriendRepository.countUserFriendByUserUuid(userUuid);
    }

    @Transactional
    public int update(final String userUuid, final List<ContactDto> contacts) {

        userFriendRepository.deleteAllByUserUuid(userUuid);

        return userFriendRepository.saveAll(contacts.stream()
            .map(contactDto -> UserFriend.of(userUuid, contactDto.phoneNumber(), contactDto.name()))
            .toList())
            .size();
    }
}
