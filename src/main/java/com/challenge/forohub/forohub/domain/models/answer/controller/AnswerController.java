package com.challenge.forohub.forohub.domain.models.answer.controller;

import com.challenge.forohub.forohub.domain.models.answer.dto.AnswerDTO;
import com.challenge.forohub.forohub.domain.models.answer.persistence.AnswerRepository;
import com.challenge.forohub.forohub.domain.models.answer.service.IAnswerService;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/answer")
public class AnswerController {

  private IAnswerService service;

  public AnswerController(AnswerRepository repository, IAnswerService service) {
    this.service = service;
  }

  @PostMapping("user/{user_id}/topic/{topic_id}")
  @Transactional(readOnly = false)
  public ResponseEntity<?> answerTopic(@Valid @RequestBody AnswerDTO dto, @PathVariable Long user_id, @PathVariable Long topic_id) {
    return service.answerTopic(dto, user_id, topic_id);
  }
}
