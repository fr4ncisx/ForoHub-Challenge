package com.challenge.forohub.forohub.domain.models.topic.dto;

import java.time.LocalDate;
import java.util.List;

import com.challenge.forohub.forohub.domain.models.answer.dto.AnswerDetailsDTO;
import com.challenge.forohub.forohub.domain.models.enums.Status;
import com.challenge.forohub.forohub.domain.models.topic.persistence.Topic;

public record TopicDTO(
    String title,
    String message,
    String username,
    LocalDate date,
    Status status,
    String course,
    List<AnswerDetailsDTO> answers) {

  public TopicDTO(Topic topic) {
    this(
        topic.getTitle(),
        topic.getMessage(),
        topic.getUser().getUsername(),
        topic.getDate(),
        topic.getStatus(),
        topic.getCourse(),
        topic.detailedAnswers());
  }
}
