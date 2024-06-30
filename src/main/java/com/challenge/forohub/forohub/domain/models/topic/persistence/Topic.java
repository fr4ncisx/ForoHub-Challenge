package com.challenge.forohub.forohub.domain.models.topic.persistence;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import com.challenge.forohub.forohub.domain.models.answer.dto.AnswerDetailsDTO;
import com.challenge.forohub.forohub.domain.models.answer.persistence.Answer;
import com.challenge.forohub.forohub.domain.models.enums.Status;
import com.challenge.forohub.forohub.domain.models.topic.dto.TopicValidationDTO;
import com.challenge.forohub.forohub.domain.models.user.persistence.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Topic {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;
  private String message;

  @Column(name = "creation_date")
  private LocalDate date;

  @Enumerated(EnumType.STRING)
  private Status status;
  private String course;

  @ManyToOne(fetch = FetchType.LAZY)
  private User user;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
  @JoinColumn(name = "topic_id")
  private List<Answer> answer;

  public Topic(TopicValidationDTO validationDTO, User userFound) {
    this.title = validationDTO.title();
    this.message = validationDTO.message();
    this.date = LocalDate.now();
    this.status = Status.ACTIVO;
    this.course = validationDTO.course();
    this.user = userFound;
  }

  public Topic(Long topic_id) {
    this.id = topic_id;
  }

  public List<AnswerDetailsDTO> detailedAnswers() {
    return answer.stream()
        .map(a -> new AnswerDetailsDTO(a.getUser().getUsername(), a.getMessage(), a.getDate(), a.getSolved()))
        .collect(Collectors.toList());
  }

  public void updateTopic(TopicValidationDTO validationDTO) {
    if (!validationDTO.title().isBlank()) {
      this.title = validationDTO.title();
    }
    if (!validationDTO.message().isBlank()) {
      this.message = validationDTO.message();
    }
    if (!validationDTO.course().isBlank()) {
      this.course = validationDTO.course();
    }
  }
}
