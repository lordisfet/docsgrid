package com.example.docsgrid.entity;

import com.example.docsgrid.entity.compoundID.DocToCompanyId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocToCompany implements IEntity {
    @EmbeddedId
    private DocToCompanyId id;

    @ManyToOne
    @MapsId("docId")
    private Company company;

    @ManyToOne
    @MapsId("companyId")
    private Doc doc;
}
