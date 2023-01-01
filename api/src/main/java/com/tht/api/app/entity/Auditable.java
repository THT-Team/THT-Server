package com.tht.api.app.entity;

import com.tht.api.enums.EntityState;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;

import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
public class Auditable implements Serializable {

    @Column
    @Enumerated(EnumType.STRING)
    private EntityState status = EntityState.ACTIVE;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "lastModified_at")
    private LocalDateTime lastModifiedAt = LocalDateTime.now();

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "last_modified_by")
    private String lastModifiedBy;
}
