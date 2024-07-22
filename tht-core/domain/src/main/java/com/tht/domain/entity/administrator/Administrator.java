package com.tht.domain.entity.administrator;

import com.tht.enums.user.UserRole;
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
    private UserRole role;
}
