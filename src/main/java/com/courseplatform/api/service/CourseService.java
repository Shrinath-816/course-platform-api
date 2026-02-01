package com.courseplatform.api.service;

import com.courseplatform.api.dto.CourseSummaryDto;
import com.courseplatform.api.entity.Course;
import com.courseplatform.api.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.courseplatform.api.dto.*;
import com.courseplatform.api.entity.Course;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    @Transactional(readOnly = true)
    public List<CourseSummaryDto> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(this::toSummary)
                .toList();
    }


    private CourseSummaryDto toSummary(Course course) {
        int topicCount = course.getTopics().size();
        int subtopicCount = course.getTopics().stream()
                .mapToInt(t -> t.getSubtopics().size())
                .sum();

        return new CourseSummaryDto(
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                topicCount,
                subtopicCount
        );
    }


    @Transactional(readOnly = true)
    public CourseDetailDto getCourseById(String id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));

        return new CourseDetailDto(
                course.getId(),
                course.getTitle(),
                course.getDescription(),
                course.getTopics().stream()
                        .map(t -> new TopicDto(
                                t.getId(),
                                t.getTitle(),
                                t.getSubtopics().stream()
                                        .map(s -> new SubtopicDto(
                                                s.getId(),
                                                s.getTitle(),
                                                s.getContent()
                                        ))
                                        .toList()
                        ))
                        .toList()
        );
    }


}

