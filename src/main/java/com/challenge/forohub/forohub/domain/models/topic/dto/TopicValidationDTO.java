package com.challenge.forohub.forohub.domain.models.topic.dto;

import jakarta.validation.constraints.NotBlank;

public record TopicValidationDTO(
  @NotBlank String title,
  @NotBlank String message,
  @NotBlank String course
) {}
