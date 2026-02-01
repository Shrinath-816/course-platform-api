package com.courseplatform.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@Getter
@AllArgsConstructor
public class CompletedItemDto {
    private String subtopicId;
    private String subtopicTitle;
    private Instant completedAt;
}

