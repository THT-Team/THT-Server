package com.tht.api.app.facade.user.request;

import jakarta.validation.constraints.NotEmpty;

public record UserReportRequest(
    @NotEmpty(message = "신고할 유저 정보가 비어있습니다.") String reportUserUuid,
    @NotEmpty(message = "신고 사유를 입력해주세요") String reason
) {

}
