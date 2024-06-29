package com.challenge.forohub.forohub.infra.errors;

import org.springframework.validation.FieldError;

public record ValidationErrorDTO(String field, String error) {
  public ValidationErrorDTO(FieldError error) {
    this(error.getField(), error.getDefaultMessage());
  }
}
