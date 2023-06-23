package com.tht.api.app.facade.user;

import com.tht.api.app.facade.user.response.MainScreenUserInfoResponse;
import com.tht.api.app.repository.mapper.MainScreenUserInfoMapper;
import com.tht.api.app.service.UserDailyFallingService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@ExtendWith(MockitoExtension.class)
@SpringBootTest
class UserFacadeTest {

    @Autowired
    UserDailyFallingService userDailyFallingService;

    @Autowired
    UserFacade userFacade;

    //todo.삭제

    @Test
    void test() {
        List<MainScreenUserInfoMapper> result = userDailyFallingService.findAllMatchingFallingUser(
            5, List.of("uuid1", "uuid2", "uuid3"),
            null, "2845dd3ea0-cb8d-43be-b096-be8041df9b90", null);

        Map<String, List<MainScreenUserInfoMapper>> collect = result.stream()
            .collect(Collectors.groupingBy(MainScreenUserInfoMapper::userUuid));

        final List<MainScreenUserInfoResponse> responses = new ArrayList<>();

        for (String userUuid : collect.keySet()) {
            List<MainScreenUserInfoMapper> mapperList = collect.get(userUuid);

            responses.add(MainScreenUserInfoResponse.of(mapperList));
        }

        System.out.println("result = " + collect);
        System.out.println("responses = " + responses);
    }

}