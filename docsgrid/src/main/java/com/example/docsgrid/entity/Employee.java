package com.example.docsgrid.entity;

import jakarta.persistence.ManyToOne;

public class Employee extends BaseEntity {
    String name;
    String job;

    @ManyToOne
    private Company company;
}
