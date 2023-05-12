package com.tht.api.app.repository.mapper;

import com.querydsl.core.annotations.QueryProjection;
import com.tht.api.app.entity.enums.SNSType;
import lombok.Value;

@Value
public class UserSnsMapper{

    String phoneNumber;
    String userUuid;
    SNSType snsType;

    @QueryProjection
    public UserSnsMapper(final String phoneNumber, final String userUuid, final SNSType snsType) {
        this.phoneNumber = phoneNumber;
        this.userUuid = userUuid;
        this.snsType = snsType;
    }
}
