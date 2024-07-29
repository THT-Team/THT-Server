package com.tht.thtadmin.ui.user.response;

public record BlockedUserInfoDto(
   String userUuid,
   String blockedUserName,
   String blockDateTime
) { }
