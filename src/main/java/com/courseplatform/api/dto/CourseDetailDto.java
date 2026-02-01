package com.courseplatform.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class CourseDetailDto {
    private String id;
    private String title;
    private String description;
    private List<TopicDto> topics;
}

