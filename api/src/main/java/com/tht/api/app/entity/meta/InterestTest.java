package com.tht.api.app.entity.meta;

import jakarta.persistence.*;

@Entity
@Table(name = "interest")
public class InterestTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @Column(name = "name")
    private String name;
}
