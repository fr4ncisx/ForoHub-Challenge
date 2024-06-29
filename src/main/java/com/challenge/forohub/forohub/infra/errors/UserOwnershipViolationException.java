package com.challenge.forohub.forohub.infra.errors;

public class UserOwnershipViolationException extends RuntimeException{
    private DetailsErrorDTO error;

    public UserOwnershipViolationException(String message){
        this.error = new DetailsErrorDTO(message);
    }

    public DetailsErrorDTO getError() {
        return error;
    }
}
