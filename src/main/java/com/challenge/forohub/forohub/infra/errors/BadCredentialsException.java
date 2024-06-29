package com.challenge.forohub.forohub.infra.errors;

public class BadCredentialsException extends RuntimeException{

    private DetailsErrorDTO error;    

    public BadCredentialsException(String message) {
        this.error = new DetailsErrorDTO(message);
    }

    public DetailsErrorDTO getError() {
        return error;
    }    

}
