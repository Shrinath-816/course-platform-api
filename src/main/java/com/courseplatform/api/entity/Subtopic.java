package com.courseplatform.api.entity;



import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "subtopics")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subtopic {

    @Id
    private String id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    private Topic topic;
}

