package com.tht.api.app.entity.meta;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Table(name = "ideal_type")
public class IdealType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @Column(name = "name")
    private String name;

    @Column(name = "emoji_code")
    private String emojiCode;

    public static IdealType of(final String name, final String emojiCode) {
        return new IdealType(null, name, emojiCode);
    }
}
