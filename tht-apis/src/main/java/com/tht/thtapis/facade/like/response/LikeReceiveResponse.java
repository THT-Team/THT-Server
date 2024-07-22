package com.tht.thtapis.facade.like.response;

import com.tht.domain.entity.like.LikeReceiveMapper;
import com.tht.thtcommonutils.utils.ConvertAgeUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record LikeReceiveResponse(

    long dailyFallingIdx,
    long likeIdx,
    String topic,
    String issue,
    String userUuid,
    String username,
    String profileUrl,
    int age,
    String address,
    String receivedTime
) {

    public static LikeReceiveResponse of(long dailyFallingIdx, long likeIdx, String topic, String issue,
        String userUuid, String username, String profileUrl, int age, String address,
        LocalDateTime createAt) {

        return new LikeReceiveResponse(dailyFallingIdx, likeIdx, topic, issue, userUuid, username,
            profileUrl, age, address, convertDateFormat(createAt));
    }

    private static String convertDateFormat(LocalDateTime createAt) {
        return createAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static LikeReceiveResponse toResponse(final LikeReceiveMapper mapper) {
        return new LikeReceiveResponse(
            mapper.dailyFallingIdx(),
            mapper.likeIdx(),
            mapper.topic(),
            mapper.issue(),
            mapper.userUuid(),
            mapper.username(),
            mapper.profileUrl(),
            ConvertAgeUtils.covertBeforeBirthAge(mapper.birthDay()),
            mapper.address(),
            convertDateFormat(mapper.receivedTime())
        );
    }
}
