package com.tht.api.app.facade.user;

import com.tht.api.app.facade.Facade;
import com.tht.api.app.facade.user.group.MainScreenUserInfoResponseGroup;
import com.tht.api.app.facade.user.request.MainScreenUserInfoRequest;
import com.tht.api.app.facade.user.response.MainScreenUserInfoResponse;
import com.tht.api.app.repository.mapper.MainScreenUserInfoMapper;
import com.tht.api.app.service.UserDailyFallingService;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Facade
@Transactional
@RequiredArgsConstructor
public class UserFacade {

    private final UserDailyFallingService userDailyFallingService;

    public List<MainScreenUserInfoResponse> findAllToDayFallingUserList(final String userUuid,
        final MainScreenUserInfoRequest request) {

        //user 가 선택한 그날의 주제어 조회 및 검증
        final long dailyFallingIdx = userDailyFallingService.findToDayFalling(userUuid);

        //해당 주제어를 선택한 user list 반환
        final Map<String, List<MainScreenUserInfoMapper>> listMap = userDailyFallingService.findAllMatchingFallingUser(
                dailyFallingIdx,
                request.alreadySeenUserUuidList(), request.userDailyFallingCourserIdx(),
                userUuid, request.size())
            .stream()
            .collect(Collectors.groupingBy(MainScreenUserInfoMapper::userUuid));

        return MainScreenUserInfoResponseGroup.of(listMap).responses();
    }
}
