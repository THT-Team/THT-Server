package com.tht.api.app.facade.user.request;

import java.util.List;

public record UserFriendContactRequest(
    List<ContactDto> contacts
) {
}
