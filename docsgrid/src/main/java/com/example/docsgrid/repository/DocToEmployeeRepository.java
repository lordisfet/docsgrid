package com.example.docsgrid.repository;

import com.example.docsgrid.entity.DocToEmployee;
import com.example.docsgrid.entity.Relation;

import java.util.List;

public interface DocToEmployeeRepository extends GenericRepository<DocToEmployee> {
    List<DocToEmployee> findDocToEmployeesByRelation(Relation relation);
}
