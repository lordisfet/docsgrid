package com.example.docsgrid.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    @Getter
    private String structure;

    /**
     * Set new structure as a string with keys in {} braces.
     * There must be no duplicates or blank keys
     * @param structure New structure
     */
    public void setStructure(String structure) {
        if (structure == null || structure.isBlank()) {
            throw new IllegalArgumentException("Structure cannot be null or blank.");
        }

        if (getStructureKeys(structure, '{', '}') == null) {
            throw new IllegalArgumentException("Structure are not validated.");
        }

        this.structure = structure;
    }

    /**
     * Get keys from the structure
     * @param structure Structure as a string with keys in braces
     * @param openBrace Open brace
     * @param closeBrace Close brace
     * @return List with keys if structure validated, otherwise null
     */
    public static List<String> getStructureKeys(String structure, char openBrace, char closeBrace) {
        List<String> substrings = new ArrayList<>();
        Set<String> seen = new HashSet<>();
        int depth = 0;
        int start = -1;

        for (int i = 0; i < structure.length(); i++) {
            char c = structure.charAt(i);

            if (c == openBrace) {
                if (depth == 0) {
                    start = i + 1;
                }
                depth++;
            } else if (c == closeBrace) {
                depth--;
                if (depth < 0) {
                    // invalid structure (close brace without open brace)
                    return null;
                }
                if (depth == 0) {
                    String content = structure.substring(start, i);
                    if (content.isBlank() || !seen.add(content)) {
                        // duplicate key or blank key
                        return null;
                    }
                    substrings.add(content);
                }
            }
        }

        return depth == 0 ? substrings : null;
    }
}
