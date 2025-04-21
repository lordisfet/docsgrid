package com.example.docsgrid.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Map;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.ToString;

@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ToString
public class DocDraft extends BaseEntity {
    @Lob
    @Column(columnDefinition = "TEXT")
    private String structure;

    public DocDraft(Map<String, Object> structureMap) throws JsonProcessingException {
        setStructure(structureMap);
    }

    public void setStructure(Map<String, Object> structureMap) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        this.structure = objectMapper.writeValueAsString(structureMap);
    }

    public Map<String, Object> getStructure() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(this.structure, Map.class);
    }
}
