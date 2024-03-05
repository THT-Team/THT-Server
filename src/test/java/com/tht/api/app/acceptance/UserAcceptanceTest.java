package com.tht.api.app.acceptance;

import static com.tht.api.app.acceptance.UserAcceptanceStep.그날의_대화토픽_선택_요청;
import static com.tht.api.app.acceptance.UserAcceptanceStep.메인화면_조회_요청;
import static com.tht.api.app.acceptance.UserAcceptanceStep.신규유저_생성_요청_후_토큰추출;
import static com.tht.api.app.acceptance.UserAcceptanceStep.유저_기기_연락처_차단_리스트_조회_요청;
import static com.tht.api.app.acceptance.UserAcceptanceStep.유저_기기_연락처_차단_요청;
import static com.tht.api.app.acceptance.UserAcceptanceStep.유저계정_탈퇴_요청;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.tht.api.app.acceptance.config.AcceptanceTest;
import com.tht.api.app.facade.user.request.ContactDto;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserAcceptanceTest extends AcceptanceTest {

    @DisplayName("사용자 탈퇴")
    @Test
    void 사용자탈퇴() {

        //given
        var dailyFalling = 그날의주제어_생성_요청();


        //given
        var 탈퇴할_사용자 = 신규유저_생성_요청_후_토큰추출("삭제할 유저", "01012341234");
        var 일반_사용자1 = 신규유저_생성_요청_후_토큰추출("일반 사용자1", "01065689787");
        var 일반_사용자2 = 신규유저_생성_요청_후_토큰추출("일반 사용자2", "01065689737");

        그날의_대화토픽_선택_요청(dailyFalling.getIdx(), 탈퇴할_사용자);
        그날의_대화토픽_선택_요청(dailyFalling.getIdx(), 일반_사용자1);
        그날의_대화토픽_선택_요청(dailyFalling.getIdx(), 일반_사용자2);

        //when
        var 삭제요청결과 = 유저계정_탈퇴_요청(탈퇴할_사용자);
        var 메인화면조회결과 = 메인화면_조회_요청(일반_사용자1);

        //then
        assertAll(
            () -> assertThat(삭제요청결과.statusCode()).isEqualTo(204),
            () -> assertThat(메인화면조회결과.jsonPath().getList("userInfos.username")).isNotEmpty(),
            () -> assertThat(메인화면조회결과.jsonPath().getList("userInfos.username")).doesNotContain(
                "삭제할 유저")
        );

    }

    /**
     * feature : 사용자 디바이스에 저장된 연락처 차단(친구로 등록)
     * <br> scenario
     * <br> - 사용자가 업데이트를 하면 유저 연락처와 연락처 이름을 불러움
     * <br> - 기존에 저장된 데이터 전부 삭제
     * <br> - 새로 불러운 데이터 저장
     * <br> given
     * <br> - 사용자 생성 and 기존 연락처 저장
     * <br> when
     * <br> - 사용자 연락처 업데이트
     * <br> then
     * <br> - 새롭게 저장된 연락처 개수를 응답 and 기존연락처 삭제 확인
     */
    @DisplayName("사용자 아는사람 차단하기")
    @Test
    void updateMyFriend() {

        //given
        var 일반_사용자1 = 신규유저_생성_요청_후_토큰추출("일반 사용자1", "01065689787");

        유저_기기_연락처_차단_요청(일반_사용자1, 차단할_연락처_리스트(5));

        var givenResponse = 유저_기기_연락처_차단_리스트_조회_요청(일반_사용자1);
        assertThat(givenResponse.jsonPath().getInt("count")).isEqualTo(5);

        //when
        유저_기기_연락처_차단_요청(일반_사용자1, 차단할_연락처_리스트(1));

        //then
        var Response = 유저_기기_연락처_차단_리스트_조회_요청(일반_사용자1);
        assertThat(Response.jsonPath().getInt("count")).isEqualTo(1);
    }

    private Map<String, Object> 차단할_연락처_리스트(int number) {

        List<ContactDto> contacts = new ArrayList<>();

        for (int i = 0; i < number; i++) {
            contacts.add(new ContactDto("친구"+i, "0" + 101234123+i));
        }


        Map<String, Object> map = new HashMap<>();
        map.put("contacts", contacts);

        return map;
    }
}
