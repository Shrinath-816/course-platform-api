package com.courseplatform.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class SearchResultDto {
    private String courseId;
    private String courseTitle;
    private List<SearchMatchDto> matches;
}

