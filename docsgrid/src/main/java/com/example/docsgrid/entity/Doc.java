package com.example.docsgrid.entity;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

public class Doc extends BaseEntity {
    private Map<String, Objects> content;
    private Date signedAt;

    private DocDraft draft;
}
