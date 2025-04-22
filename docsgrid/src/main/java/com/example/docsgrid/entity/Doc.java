package com.example.docsgrid.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@ToString
public class Doc extends BaseEntity {
    // https://stackoverflow.com/questions/3393649/storing-a-mapstring-string-using-jpa
    @Getter
    @ElementCollection
    @MapKeyColumn(name="key")
    @Column(name="value")
    @CollectionTable(name="content_keys_values", joinColumns=@JoinColumn(name="doc_id"))
    private Map<String, String> content;

    @Getter
    @Setter
    private LocalDateTime signedAt;

    @Getter
    @Setter
    @ManyToOne
    private DocDraft draft;

    public void setContent(Map<String, String> content) {
        if (draft == null) {
            throw new IllegalArgumentException("Draft must first be installed in order to recognize the content keys");
        }

        List<String> draftStructureKeys = DocDraft.getStructureKeys(draft.getStructure(), '{', '}');
        if (draftStructureKeys == null) {
            throw new IllegalArgumentException("Draft structure are not validated, but saved in storage");
        }

        for (String value : content.values()) {
            if (value == null || value.isBlank()) {
                throw new IllegalArgumentException("Value cannot be null or blank");
            }
        }

        Set<String> draftStructureKeysSet = new HashSet<>(draftStructureKeys);

        if (!draftStructureKeysSet.equals(content.keySet())) {
            throw new IllegalArgumentException("Content and structure keys don't match. Keys:" + draftStructureKeysSet);
        }

        this.content = content;
    }

    /**
     * Find placeholders with keys in the draft structure and change it to content values
     * @return Ready content
     */
    public String getReadyContent() {
        if (draft == null) {
            throw new IllegalArgumentException("Draft not set");
        }

        Pattern pattern = Pattern.compile("\\{([^}]+)}");
        Matcher matcher = pattern.matcher(draft.getStructure());

        StringBuilder result = new StringBuilder();
        while (matcher.find()) {
            String key = matcher.group(1);
            String replacement = content.getOrDefault(key, matcher.group(0)); // if key unknown for some reason
            matcher.appendReplacement(result, Matcher.quoteReplacement(replacement));
        }
        matcher.appendTail(result);

        return result.toString();
    }
}
