package com.challenge.forohub.forohub.domain.models.answer.service;

import org.springframework.http.ResponseEntity;

import com.challenge.forohub.forohub.domain.models.answer.dto.AnswerDTO;

import jakarta.validation.Valid;


public interface IAnswerService {
    public ResponseEntity<?> answerTopic(@Valid AnswerDTO dto, Long user_id, Long topic_id);
    
}
