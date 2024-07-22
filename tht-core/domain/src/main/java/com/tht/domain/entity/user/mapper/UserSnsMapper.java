package com.tht.domain.entity.user.mapper;

import com.querydsl.core.annotations.QueryProjection;
import com.tht.enums.user.SNSType;

public record UserSnsMapper(

    String phoneNumber,
    String userUuid,
    SNSType snsType
){

    @QueryProjection
    public UserSnsMapper {
    }
}
