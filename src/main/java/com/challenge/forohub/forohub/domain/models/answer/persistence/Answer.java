package com.challenge.forohub.forohub.domain.models.answer.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;

import com.challenge.forohub.forohub.domain.models.answer.dto.AnswerDTO;
import com.challenge.forohub.forohub.domain.models.enums.Solved;
import com.challenge.forohub.forohub.domain.models.topic.persistence.Topic;
import com.challenge.forohub.forohub.domain.models.user.persistence.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Answer {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String message;

  @ManyToOne
  private Topic topic;

  @Column(name = "creation_date")
  private LocalDate date;

  @OneToOne
  private User user;
  @Enumerated(EnumType.STRING)
  private Solved solved;

  public Answer(AnswerDTO dto, Long user_id, Long topic_id) {
    this.message = dto.message();
    this.topic = new Topic(topic_id);
    this.date = LocalDate.now();
    this.user = new User(user_id);
    this.solved = Solved.NO;
  }
}
