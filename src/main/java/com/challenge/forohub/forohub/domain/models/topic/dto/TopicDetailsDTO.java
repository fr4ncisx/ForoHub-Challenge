package com.challenge.forohub.forohub.domain.models.topic.dto;

import java.time.LocalDate;

import com.challenge.forohub.forohub.domain.models.enums.Status;
import com.challenge.forohub.forohub.domain.models.topic.persistence.Topic;

public record TopicDetailsDTO(
        String title,
        String message,
        String username,
        LocalDate date,
        Status status,
        String course) {

    public TopicDetailsDTO(Topic topic) {
        this(topic.getTitle(),
            topic.getMessage(),
            topic.getUser().getUsername(),
            topic.getDate(),
            topic.getStatus(),
            topic.getCourse());
    }

}
