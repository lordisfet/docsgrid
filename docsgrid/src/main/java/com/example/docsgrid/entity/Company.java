package com.example.docsgrid.entity;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Company extends BaseEntity {
    String name;
    String BIC;
}
