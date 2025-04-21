package com.example.docsgrid.entity.compoundID;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocToCompanyId implements Serializable {
    private Long docId;
    private Long companyId;
}