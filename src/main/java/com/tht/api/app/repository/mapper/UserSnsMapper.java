package com.tht.api.app.repository.mapper;

import com.querydsl.core.annotations.QueryProjection;
import com.tht.api.app.entity.enums.SNSType;

public record UserSnsMapper(

    String phoneNumber,
    String userUuid,
    SNSType snsType
){

    @QueryProjection
    public UserSnsMapper {
    }
}
