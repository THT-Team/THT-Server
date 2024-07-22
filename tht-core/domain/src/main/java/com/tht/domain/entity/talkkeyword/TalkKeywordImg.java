package com.tht.domain.entity.talkkeyword;

import com.tht.domain.entity.Auditable;
import jakarta.persistence.*;

@Entity
@Table
public class TalkKeywordImg extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @Column
    private String imgUrl;

    @Column
    private String name;
}
