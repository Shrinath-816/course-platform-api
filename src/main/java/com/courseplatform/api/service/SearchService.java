package com.courseplatform.api.service;

import com.courseplatform.api.dto.SearchMatchDto;
import com.courseplatform.api.dto.SearchResultDto;
import com.courseplatform.api.entity.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final com.courseplatform.api.repository.CourseRepository courseRepository;

    @Transactional(readOnly = true)
    public List<SearchResultDto> search(String q) {
        List<Course> courses = courseRepository.searchCourses(q);
        List<SearchResultDto> results = new ArrayList<>();

        for (Course c : courses) {
            List<SearchMatchDto> matches = new ArrayList<>();

            c.getTopics().forEach(t ->
                    t.getSubtopics().forEach(s -> {
                        String text = (s.getTitle() + " " + s.getContent()).toLowerCase();
                        if (text.contains(q.toLowerCase())) {
                            matches.add(new SearchMatchDto(
                                    "subtopic",
                                    t.getTitle(),
                                    s.getId(),
                                    s.getTitle(),
                                    s.getContent().substring(0, Math.min(60, s.getContent().length()))
                            ));
                        }
                    })
            );

            results.add(new SearchResultDto(c.getId(), c.getTitle(), matches));
        }
        return results;
    }
}

