package com.challenge.forohub.forohub.infra.errors;

public class EmptyDatabaseError extends RuntimeException {

  public EmptyDatabaseError(String message) {
    super(message);
  }
}
