package com.courseplatform.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TopicDto {
    private String id;
    private String title;
    private List<SubtopicDto> subtopics;
}

