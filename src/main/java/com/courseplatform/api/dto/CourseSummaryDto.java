package com.courseplatform.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CourseSummaryDto {

    private String id;
    private String title;
    private String description;
    private int topicCount;
    private int subtopicCount;
}

