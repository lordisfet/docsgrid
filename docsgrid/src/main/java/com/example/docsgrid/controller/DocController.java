package com.example.docsgrid.controller;

import com.example.docsgrid.dto.DocCreateDto;
import com.example.docsgrid.dto.DocFullReadDto;
import com.example.docsgrid.entity.Doc;
import com.example.docsgrid.service.DocService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/docs")
public class DocController {
    private final DocService docService;

    public DocController(DocService docService) {
        this.docService = docService;
    }

    @PostMapping
    public Doc createDoc(@RequestBody DocCreateDto docCreateDto) {
        return this.docService.createDoc(
                docCreateDto.getDraftId(), docCreateDto.getContent(), docCreateDto.getSignedAt());
    }

    @GetMapping
    public List<Doc> getAllDocs() {
        return this.docService.findAllDocs();
    }

    @GetMapping("/search/by-draft-id")
    public List<Doc> getAllDocsByDraftID(@RequestParam Long draftId) {
        return this.docService.findAllDocsByDraftId(draftId);
    }

    @GetMapping("/{id}")
    public DocFullReadDto getDocByID(@RequestParam Long id) {
        return this.docService.findDocById(id);
    }
}
