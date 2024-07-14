package com.tht.infra.user.mapper;

import com.querydsl.core.annotations.QueryProjection;
import com.tht.infra.user.enums.SNSType;

public record UserSnsMapper(

    String phoneNumber,
    String userUuid,
    SNSType snsType
){

    @QueryProjection
    public UserSnsMapper {
    }
}
