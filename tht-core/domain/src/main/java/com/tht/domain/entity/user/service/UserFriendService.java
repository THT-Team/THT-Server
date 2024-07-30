package com.tht.domain.entity.user.service;

import java.util.List;

import com.tht.domain.entity.user.UserFriend;
import com.tht.domain.entity.user.repository.UserFriendRepository;
import com.tht.domain.entity.user.service.dto.ContactDto;
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

        List<Long> userFriendIdxList = userFriendRepository.findAllByUserUuid(userUuid)
            .stream().map(UserFriend::getIdx)
            .toList();

        userFriendRepository.deleteAllByIdInBatch(userFriendIdxList);

        return userFriendRepository.saveAll(contacts.stream()
            .map(contactDto -> UserFriend.of(userUuid, contactDto.phoneNumber(), contactDto.name()))
            .toList())
            .size();
    }
}
