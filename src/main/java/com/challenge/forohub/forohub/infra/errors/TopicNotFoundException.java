package com.challenge.forohub.forohub.infra.errors;

public class TopicNotFoundException extends RuntimeException{
    private DetailsErrorDTO error;

    public TopicNotFoundException(String message) {
        this.error = new DetailsErrorDTO(message);
    }
    public DetailsErrorDTO getError() {
        return error;
    } 
}
