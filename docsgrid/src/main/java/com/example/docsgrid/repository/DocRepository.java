package com.example.docsgrid.repository;

import com.example.docsgrid.entity.Doc;
import com.example.docsgrid.entity.DocDraft;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDateTime;
import java.util.List;

@RepositoryRestResource(exported = false)
public interface DocRepository extends GenericRepository<Doc> {
    List<Doc> findDocsByDraft(DocDraft draft);
    List<Doc> findDocsBySignedAtBetween(LocalDateTime signedAtAfter, LocalDateTime signedAtBefore);
}
