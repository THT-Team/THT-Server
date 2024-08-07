package com.tht.thtadmin.ui.user;

import com.tht.thtadmin.ui.user.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserManageController {

    private final UserManageUseCase userManageUseCase;

    @GetMapping("/users")
    public ResponseEntity<Page<UserSimpleListResponse>> getUsers(@RequestParam(required = false) String search,
                                                                 @PageableDefault(size = 100) Pageable pageable) {

        return ResponseEntity.ok(userManageUseCase.getUserList(search, pageable));
    }

    @GetMapping("/user/{user-uuid}")
    public ResponseEntity<UserDetailResponse> getUser(@PathVariable(value = "user-uuid") String userUuid) {

        return ResponseEntity.ok(userManageUseCase.getUserDetail(userUuid));
    }

    @GetMapping("/users/block")
    public ResponseEntity<Page<UserBlockResponse>> getBlockList(@PageableDefault(size = 100) Pageable pageable) {

        final Page<UserBlockResponse> responses = userManageUseCase.getBlockUserList(pageable);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/users/report")
    public ResponseEntity<Page<UserReportResponse>> getUserReport(@PageableDefault(size = 100) Pageable pageable) {

        final Page<UserReportResponse> responses = userManageUseCase.getReportUserList(pageable);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/users/withdraw")
    public ResponseEntity<Page<WithDrawUserResponse>> getWithDrawUser(@PageableDefault(size = 100) Pageable pageable) {

        final Page<WithDrawUserResponse> responses = userManageUseCase.getWithDrawList(pageable);
        return ResponseEntity.ok(responses);
    }

}
