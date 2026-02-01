package com.courseplatform.api.controller;

import com.courseplatform.api.dto.EnrollmentResponse;
import com.courseplatform.api.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping("/{courseId}/enroll")
    public EnrollmentResponse enroll(
            @PathVariable String courseId,
            Authentication authentication
    ) {
        String email = authentication.getName();
        return enrollmentService.enroll(courseId, email);
    }
}

