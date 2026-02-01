package com.courseplatform.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SearchMatchDto {
    private String type;          // topic | subtopic | content
    private String topicTitle;
    private String subtopicId;
    private String subtopicTitle;
    private String snippet;
}

