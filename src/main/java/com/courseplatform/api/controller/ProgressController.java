package com.courseplatform.api.controller;

import com.courseplatform.api.dto.EnrollmentProgressDto;
import com.courseplatform.api.dto.SubtopicProgressResponse;
import com.courseplatform.api.service.ProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subtopics")
@RequiredArgsConstructor
public class ProgressController {

    private final ProgressService progressService;

    @PostMapping("/{subtopicId}/complete")
    public SubtopicProgressResponse complete(
            @PathVariable String subtopicId,
            Authentication authentication
    ) {
        return progressService.completeSubtopic(
                subtopicId,
                authentication.getName()
        );
    }


    @GetMapping("/enrollments/{enrollmentId}/progress")
    public EnrollmentProgressDto progress(
            @PathVariable Long enrollmentId,
            Authentication authentication
    ) {
        return progressService.getProgress(
                enrollmentId,
                authentication.getName()
        );
    }

}

