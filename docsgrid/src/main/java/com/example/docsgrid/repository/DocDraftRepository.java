package com.example.docsgrid.repository;

import com.example.docsgrid.entity.DocDraft;

import java.util.List;
import java.util.Optional;

public interface DocDraftRepository extends GenericRepository<DocDraft> {
    Optional<DocDraft> findDocDraftByTitleIsIgnoreCase(String title);
}
