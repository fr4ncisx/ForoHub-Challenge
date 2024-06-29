package com.challenge.forohub.forohub.infra.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalErrorHandler {

  @ExceptionHandler(EmptyDatabaseError.class)
  public ResponseEntity<String> emptyDatabase(Exception e) {
    return ResponseEntity.badRequest().body(e.getMessage());
  }

  @ExceptionHandler(DuplicatedEntryError.class)
  public ResponseEntity<?> handleDuplicatedUserError(DuplicatedEntryError ex) {
    var errorDetails = ex.getError();
    return ResponseEntity.badRequest().body(errorDetails);
  }

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<?> userNotFound(UserNotFoundException ex) {
    var errorDetails = ex.getError();
    return ResponseEntity.badRequest().body(errorDetails);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<?> emptyRequestBody(
    HttpMessageNotReadableException ex
  ) {
    DetailsErrorDTO error = new DetailsErrorDTO(
      "Por favor verifica que el body sea correcto o no esté vacio."
    );
    return ResponseEntity.badRequest().body(error);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> validationExceptions(
    MethodArgumentNotValidException ex) {
    var errors = ex.getFieldErrors().stream().map(ValidationErrorDTO::new).toList();
    return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
  }
  @ExceptionHandler(TopicNotFoundException.class)
  public ResponseEntity<?> topicNotFound(TopicNotFoundException ex){
    var error = ex.getError();
    return ResponseEntity.badRequest().body(error);
  }
  @ExceptionHandler(ViolationRoleException.class)
  public ResponseEntity<?> notAllowedUsername(ViolationRoleException ex){
    var error = ex.getError();
    return ResponseEntity.badRequest().body(error);
  }
}
