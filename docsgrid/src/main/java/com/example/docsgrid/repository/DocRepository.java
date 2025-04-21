package com.example.docsgrid.repository;

import com.example.docsgrid.entity.Doc;
import com.example.docsgrid.entity.DocDraft;

import java.time.LocalDateTime;
import java.util.List;

public interface DocRepository extends GenericRepository<Doc> {
    List<Doc> findDocsByDraft(DocDraft draft);
    List<Doc> findDocsBySignedAtBetween(LocalDateTime signedAtAfter, LocalDateTime signedAtBefore);
}
