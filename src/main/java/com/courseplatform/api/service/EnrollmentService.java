package com.courseplatform.api.service;

import com.courseplatform.api.dto.EnrollmentResponse;
import com.courseplatform.api.entity.Course;
import com.courseplatform.api.entity.Enrollment;
import com.courseplatform.api.entity.User;
import com.courseplatform.api.repository.CourseRepository;
import com.courseplatform.api.repository.EnrollmentRepository;
import com.courseplatform.api.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Transactional
    public EnrollmentResponse enroll(String courseId, String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found"));

        enrollmentRepository.findByUserIdAndCourseId(user.getId(), courseId)
                .ifPresent(e -> {
                    throw new IllegalStateException("Already enrolled");
                });

        Enrollment enrollment = new Enrollment();
        enrollment.setUser(user);
        enrollment.setCourse(course);
        enrollment.setEnrolledAt(Instant.now());

        Enrollment saved = enrollmentRepository.save(enrollment);

        return new EnrollmentResponse(
                saved.getId(),
                course.getId(),
                course.getTitle(),
                saved.getEnrolledAt()
        );
    }
}

