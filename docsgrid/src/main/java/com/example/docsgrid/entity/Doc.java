package com.example.docsgrid.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ToString
public class Doc extends BaseEntity {
    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;
    private Date signedAt;

    @ManyToOne
    private DocDraft draft;

    public void setContent(Map<String, Objects> contentMap) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        this.content = objectMapper.writeValueAsString(contentMap);
    }

    public Map<String, Objects> getContent() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(this.content, Map.class);
    }
}
