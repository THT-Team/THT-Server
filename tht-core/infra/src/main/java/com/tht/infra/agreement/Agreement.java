package com.tht.infra.agreement;

import com.tht.enums.agreement.AgreementCategory;
import com.tht.enums.agreement.AgreementCategoryConverter;
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

    @Column
    String detailLink;
}
