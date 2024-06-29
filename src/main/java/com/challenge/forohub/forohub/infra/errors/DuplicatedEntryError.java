package com.challenge.forohub.forohub.infra.errors;


public class DuplicatedEntryError extends RuntimeException {
    private DetailsErrorDTO error;

  public DuplicatedEntryError(String message) {
    this.error = new DetailsErrorDTO(message);
  }

  public DetailsErrorDTO getError(){
    return error;
  } 
}
