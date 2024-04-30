package com.tht.api.app.entity.meta;

import com.tht.api.app.entity.enums.AgreementCategory;
import com.tht.api.app.entity.enums.converter.AgreementCategoryConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Agreement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @Column
    @Convert(converter = AgreementCategoryConverter.class)
    AgreementCategory name;

    @Column
    String subject;

    @Column
    boolean isRequired;

    @Column
    String description;
}
