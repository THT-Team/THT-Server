package com.tht.infra.talkkeyword;

import com.tht.infra.Auditable;
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
