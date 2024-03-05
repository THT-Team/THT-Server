package com.tht.api.app.entity.user;

import com.tht.api.app.entity.Auditable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@ToString
@Getter
@Table(name = "user_friend")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class UserFriend extends Auditable {

    @Id
    @Column(name = "idx")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(name = "user_uuid")
    private String userUuid;

    @Column(name = "phone_number")
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
