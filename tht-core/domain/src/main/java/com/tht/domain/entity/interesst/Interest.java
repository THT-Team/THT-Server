package com.tht.domain.entity.interesst;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Table
public class Interest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @Column
    private String name;

    @Column
    private String emojiCode;

    public static Interest of(final String name, final String emojiCode) {
        return new Interest(null, name, emojiCode);
    }
}
