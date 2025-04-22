package com.example.docsgrid.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class DocCreateDto {
    private Map<String, String> content;
    private LocalDateTime signedAt;
    private Long draftId;
}
