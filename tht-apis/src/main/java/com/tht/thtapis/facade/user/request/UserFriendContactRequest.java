package com.tht.thtapis.facade.user.request;

import java.util.List;

public record UserFriendContactRequest(
    List<UserFriendContactInfo> contacts
) {
}
