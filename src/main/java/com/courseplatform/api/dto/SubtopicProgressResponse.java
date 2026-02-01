package com.courseplatform.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class SubtopicProgressResponse {
    private String subtopicId;
    private boolean completed;
    private Instant completedAt;
}

