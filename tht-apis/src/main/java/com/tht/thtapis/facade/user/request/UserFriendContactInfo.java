package com.tht.thtapis.facade.user.request;

import com.tht.domain.entity.user.service.dto.ContactDto;

public record UserFriendContactInfo(
    String name,
    String phoneNumber
) {

    private static final String removeHyphenRegex = "[^0-9]";

    public UserFriendContactInfo(final String name, final String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber.replaceAll(removeHyphenRegex, "");
    }

    public ContactDto toContactDto() {
        return new ContactDto(name, phoneNumber);
    }

}
