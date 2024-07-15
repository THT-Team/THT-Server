package com.tht.infra.user;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class UserAccountWithdrawalLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long idx;

    @CreatedDate
    @Column
    private LocalDateTime createdAt;
}
