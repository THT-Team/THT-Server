package com.tht.api.app.facade.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberSignUpRequestDTO {

    public String username;

    public String password;
}
