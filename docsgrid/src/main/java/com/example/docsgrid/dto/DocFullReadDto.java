package com.example.docsgrid.dto;

import com.example.docsgrid.entity.DocDraft;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class DocFullReadDto {
    private Map<String, String> content;
    private LocalDateTime signedAt;
    private DocDraft draft;
    private String readyContent;
}
