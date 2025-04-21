package com.example.docsgrid.entity;

import com.example.docsgrid.entity.compoundID.DocToEmployeeId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocToEmployee implements IEntity {
    @EmbeddedId
    private DocToEmployeeId id;

    @ManyToOne
    private Relation relation;

    @ManyToOne
    @MapsId("docId")
    private Doc doc;

    @ManyToOne
    @MapsId("employeeId")
    private Employee employee;
}
