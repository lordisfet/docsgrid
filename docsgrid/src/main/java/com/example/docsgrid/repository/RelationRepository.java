package com.example.docsgrid.repository;

import com.example.docsgrid.entity.Relation;

import java.util.List;
import java.util.Optional;

public interface RelationRepository extends GenericRepository<Relation> {
    Optional<Relation> findRelationByTypeIsIgnoreCase(String type);
}
