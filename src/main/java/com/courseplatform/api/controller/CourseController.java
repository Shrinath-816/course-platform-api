package com.courseplatform.api.controller;

import com.courseplatform.api.dto.CourseSummaryDto;
import com.courseplatform.api.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.courseplatform.api.dto.CourseDetailDto;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public List<CourseSummaryDto> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public CourseDetailDto getCourseById(@PathVariable String id) {
        return courseService.getCourseById(id);
    }

}

