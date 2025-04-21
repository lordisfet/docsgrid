package com.example.docsgrid.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDateTime;
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
    private LocalDateTime signedAt;

    @ManyToOne
    private DocDraft draft;

    // Заглушки з e.PrintStackTrace треба потім прибрати та додати логгер
    public void setContent(Map<String, Objects> contentMap) /*throws JsonProcessingException*/ {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            this.content = objectMapper.writeValueAsString(contentMap);
        } catch (JsonProcessingException e){
            e.printStackTrace();
        }
    }

    public Map<String, Objects> getContent() /*throws JsonProcessingException*/ {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(this.content, Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
