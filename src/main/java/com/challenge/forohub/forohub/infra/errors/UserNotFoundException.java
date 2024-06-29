package com.challenge.forohub.forohub.infra.errors;

public class UserNotFoundException extends RuntimeException{
    private DetailsErrorDTO error;

    public UserNotFoundException(String message) {
        this.error = new DetailsErrorDTO(message);
    }
    public DetailsErrorDTO getError() {
        return error;
    } 
}
