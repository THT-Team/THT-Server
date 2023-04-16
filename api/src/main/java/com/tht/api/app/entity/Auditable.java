package com.tht.api.app.entity;

import com.tht.api.app.entity.enums.EntityState;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;

import java.time.LocalDateTime;

@MappedSuperclass
public class Auditable {

    @Column
    @Enumerated(EnumType.STRING)
    private EntityState state = EntityState.ACTIVE;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "lastModified_at")
    private LocalDateTime lastModifiedAt = LocalDateTime.now();
}
