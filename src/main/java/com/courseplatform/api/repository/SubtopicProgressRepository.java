package com.courseplatform.api.repository;



import com.courseplatform.api.entity.SubtopicProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubtopicProgressRepository extends JpaRepository<SubtopicProgress, Long> {
    Optional<SubtopicProgress> findByUserIdAndSubtopicId(Long userId, String subtopicId);
}

