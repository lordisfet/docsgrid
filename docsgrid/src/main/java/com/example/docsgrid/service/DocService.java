package com.example.docsgrid.service;

import com.example.docsgrid.dto.DocFullReadDto;
import com.example.docsgrid.entity.Doc;
import com.example.docsgrid.entity.DocDraft;
import com.example.docsgrid.repository.DocDraftRepository;
import com.example.docsgrid.repository.DocRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class DocService {
    private final DocRepository docRepository;
    private final DocDraftRepository docDraftRepository;

    public DocService(DocRepository docRepository, DocDraftRepository docDraftRepository) {
        this.docRepository = docRepository;
        this.docDraftRepository = docDraftRepository;
    }

    public Doc createDoc(Long draftId, Map<String, String> content, LocalDateTime signedAt) {
        DocDraft draft = this.docDraftRepository.findById(draftId)
                .orElseThrow(() -> new IllegalArgumentException("Draft not found"));

        Doc doc = new Doc();

        doc.setDraft(draft);
        doc.setContent(content);
        doc.setSignedAt(signedAt);

        return docRepository.save(doc);
    }

    public List<Doc> findAllDocs() {
        return this.docRepository.findAll();
    }

    public List<Doc> findAllDocsByDraftId(Long draftId) {
        DocDraft draft = this.docDraftRepository.findById(draftId)
                .orElseThrow(() -> new IllegalArgumentException("Draft not found"));

        return docRepository.findDocsByDraft(draft);
    }

    public DocFullReadDto findDocById(Long docId) {
        Doc doc = docRepository.findById(docId)
                .orElseThrow(() -> new IllegalArgumentException("Doc not found"));

        DocFullReadDto docFullReadDto = new DocFullReadDto();

        docFullReadDto.setDraft(doc.getDraft());
        docFullReadDto.setContent(doc.getContent());
        docFullReadDto.setSignedAt(doc.getSignedAt());
        docFullReadDto.setReadyContent(doc.getReadyContent());

        return docFullReadDto;
    }
}
