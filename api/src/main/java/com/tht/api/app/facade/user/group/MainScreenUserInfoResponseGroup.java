package com.tht.api.app.facade.user.group;

import com.tht.api.app.facade.user.response.MainScreenUserInfoResponse;
import com.tht.api.app.repository.mapper.MainScreenUserInfoMapper;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public record MainScreenUserInfoResponseGroup(
    List<MainScreenUserInfoResponse> responses
) {

    public static MainScreenUserInfoResponseGroup of(
        final Map<String, List<MainScreenUserInfoMapper>> listMap) {

        final List<MainScreenUserInfoResponse> responses = new ArrayList<>();

        for (Map.Entry<String, List<MainScreenUserInfoMapper>> entry : listMap.entrySet()) {
            List<MainScreenUserInfoMapper> mapperList = entry.getValue();

            responses.add(MainScreenUserInfoResponse.of(mapperList));
        }

        return new MainScreenUserInfoResponseGroup(
            responses.stream()
                .sorted(Comparator.comparing(MainScreenUserInfoResponse::userDailyFallingIdx))
                .toList()
        );
    }
}