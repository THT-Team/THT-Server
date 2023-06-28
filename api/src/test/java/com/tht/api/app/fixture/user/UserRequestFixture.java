package com.tht.api.app.fixture.user;

import com.tht.api.app.facade.user.request.UserReportRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRequestFixture {

    private static final  String reportUserUuid = "신고할 user uuid";
    private static final String reason = "욕설 및 비방";

    public static UserReportRequest make() {
        return new UserReportRequest(reportUserUuid, reason);
    }

}
