package com.tht.domain.entity.administrator;

import com.tht.enums.user.UserRole;
import com.tht.enums.user.converter.UserRoleConverter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
@Getter
public class Administrator {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    @Column
    private String id;
    @Column
    private String password;
    @Column
    private String username;
    @Column
    @Convert(converter = UserRoleConverter.class)
    private UserRole role;

    public Administrator(String id, String password, String username) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.role = UserRole.ADMIN;
    }
}
