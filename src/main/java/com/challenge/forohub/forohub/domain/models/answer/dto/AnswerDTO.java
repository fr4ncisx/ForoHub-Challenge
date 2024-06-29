package com.challenge.forohub.forohub.domain.models.answer.dto;

import jakarta.validation.constraints.NotBlank;

public record AnswerDTO(
  @NotBlank (message = "El mensaje no debe estar vacio") String message
) {}
