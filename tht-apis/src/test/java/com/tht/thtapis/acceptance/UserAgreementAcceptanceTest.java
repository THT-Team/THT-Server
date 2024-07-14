package com.tht.thtapis.acceptance;

import com.tht.infra.agreement.AgreementCategory;
import com.tht.thtapis.acceptance.config.AcceptanceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.tht.thtapis.acceptance.UserAcceptanceStep.신규유저_생성_요청_후_토큰추출;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class UserAgreementAcceptanceTest extends AcceptanceTest {

    @DisplayName("유저 약관동의 내역 업데이트")
    @Test
    public void updateUserAgreement() {

        //given
        String 일반_사용자1 = 신규유저_생성_요청_후_토큰추출("일반 사용자1", "01065689787");
        String agreementName = AgreementCategory.SERVICE_USE_AGREE.getValue();
        boolean userAgreementValue = false;

        //when
        var 유저약관동의내역_수정_결과 = UserAgreementStep.유저약관동의내역_수정(일반_사용자1, agreementName, userAgreementValue);
        var 유저상세조회_결과 = UserAgreementStep.유저_상세정보_조회(일반_사용자1);

        //then
        assertAll(
                () -> assertThat(유저약관동의내역_수정_결과.statusCode()).isEqualTo(200),
                () -> assertThat(유저상세조회_결과.jsonPath().getBoolean("userAgreements." + agreementName)).isEqualTo(userAgreementValue)
        );
    }

}
