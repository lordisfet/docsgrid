package com.example.docsgrid.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Map;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ToString
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"title"})
)
public class DocDraft extends BaseEntity {
    @Setter
    @Getter
    private String title;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String structure;

    public DocDraft(String title, Map<String, Object> structureMap) {
        this.title = title;
        setStructure(structureMap);
    }

    // Заглушки з e.PrintStackTrace треба потім прибрати та додати логгер
    public void setStructure(Map<String, Object> structureMap) /*throws JsonProcessingException*/ {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            this.structure = objectMapper.writeValueAsString(structureMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Object> getStructure() /*throws JsonProcessingException*/ {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(this.structure, Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
