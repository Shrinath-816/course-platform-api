package com.courseplatform.api.config;

import com.courseplatform.api.entity.Course;
import com.courseplatform.api.entity.Topic;
import com.courseplatform.api.entity.Subtopic;
import com.courseplatform.api.repository.CourseRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final CourseRepository courseRepository;
    private final ObjectMapper objectMapper;

    @Override
    public void run(String... args) throws Exception {
        if (courseRepository.count() > 0) return;

        InputStream inputStream =
                new ClassPathResource("seed/courses.json").getInputStream();

        List<Course> courses =
                objectMapper.readValue(inputStream, new TypeReference<>() {});

        for (Course course : courses) {
            for (Topic topic : course.getTopics()) {
                topic.setCourse(course);
                for (Subtopic subtopic : topic.getSubtopics()) {
                    subtopic.setTopic(topic);
                }
            }
        }

        courseRepository.saveAll(courses);
        System.out.println("Seed data loaded");
    }
}


