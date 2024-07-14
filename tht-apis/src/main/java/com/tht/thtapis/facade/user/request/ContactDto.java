package com.tht.thtapis.facade.user.request;

public record ContactDto(
    String name,
    String phoneNumber
) {

    private static final String removeHyphenRegex = "[^0-9]";

    public ContactDto(final String name, final String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber.replaceAll(removeHyphenRegex, "");
    }

}
