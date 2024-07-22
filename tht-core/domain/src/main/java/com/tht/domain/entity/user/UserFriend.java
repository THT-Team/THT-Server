package com.tht.domain.entity.user;

import com.tht.domain.entity.Auditable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@ToString
@Getter
@Table
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class UserFriend extends Auditable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private String userUuid;

    @Column
    private String phoneNumber;

    @Column
    private String name;

    private UserFriend(Long idx, String userUuid, String phoneNumber, String name) {
        this.idx = idx;
        this.userUuid = userUuid;
        this.phoneNumber = phoneNumber;
        this.name = name;
    }

    public static UserFriend of(final String userUuid, final String phoneNumber, final String name) {
        return new UserFriend(null, userUuid, phoneNumber, name);
    }

}
