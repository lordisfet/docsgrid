package com.example.docsgrid.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"BIC"}),
                @UniqueConstraint(columnNames = {"name"})
        }
)
public class Company extends BaseEntity {
    String name;
//    @Pattern(regexp = "^[0-9]+$", message = "Only digits are allowed")
    String BIC;
}
