package com.tht.infra.idealtype;

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
public class IdealType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @Column
    private String name;

    @Column
    private String emojiCode;

    public static IdealType of(final String name, final String emojiCode) {
        return new IdealType(null, name, emojiCode);
    }
}
