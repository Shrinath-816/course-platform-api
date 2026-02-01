package com.courseplatform.api.service;

import com.courseplatform.api.dto.CompletedItemDto;
import com.courseplatform.api.dto.EnrollmentProgressDto;
import com.courseplatform.api.dto.SubtopicProgressResponse;
import com.courseplatform.api.entity.*;
import com.courseplatform.api.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class ProgressService {

    private final SubtopicRepository subtopicRepository;
    private final SubtopicProgressRepository progressRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;

    @Transactional
    public SubtopicProgressResponse completeSubtopic(String subtopicId, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Subtopic subtopic = subtopicRepository.findById(subtopicId)
                .orElseThrow(() -> new EntityNotFoundException("Subtopic not found"));

        String courseId = subtopic.getTopic().getCourse().getId();

        enrollmentRepository.findByUserIdAndCourseId(user.getId(), courseId)
                .orElseThrow(() -> new SecurityException("Not enrolled in course"));

        SubtopicProgress progress = progressRepository
                .findByUserIdAndSubtopicId(user.getId(), subtopicId)
                .orElseGet(() -> {
                    SubtopicProgress p = new SubtopicProgress();
                    p.setUser(user);
                    p.setSubtopic(subtopic);
                    p.setCompleted(true);
                    p.setCompletedAt(Instant.now());
                    return p;
                });

        return new SubtopicProgressResponse(
                subtopicId,
                true,
                progressRepository.save(progress).getCompletedAt()
        );
    }

    @Transactional(readOnly = true)
    public EnrollmentProgressDto getProgress(Long enrollmentId, String email) {

        Enrollment enrollment = enrollmentRepository.findById(enrollmentId)
                .orElseThrow(() -> new EntityNotFoundException("Enrollment not found"));

        if (!enrollment.getUser().getEmail().equals(email)) {
            throw new SecurityException("Access denied");
        }

        Course course = enrollment.getCourse();

        int totalSubtopics = course.getTopics().stream()
                .mapToInt(t -> t.getSubtopics().size())
                .sum();

        var completed = progressRepository
                .findAll().stream()
                .filter(p ->
                        p.getUser().getId().equals(enrollment.getUser().getId()) &&
                                p.getSubtopic().getTopic().getCourse().getId().equals(course.getId())
                )
                .collect(Collectors.toList());

        int completedCount = completed.size();
        double percentage = totalSubtopics == 0 ? 0 :
                (completedCount * 100.0) / totalSubtopics;

        var completedItems = completed.stream()
                .map(p -> new CompletedItemDto(
                        p.getSubtopic().getId(),
                        p.getSubtopic().getTitle(),
                        p.getCompletedAt()
                ))
                .toList();

        return new EnrollmentProgressDto(
                enrollmentId,
                course.getId(),
                course.getTitle(),
                totalSubtopics,
                completedCount,
                Math.round(percentage * 100.0) / 100.0,
                completedItems
        );
    }

}

