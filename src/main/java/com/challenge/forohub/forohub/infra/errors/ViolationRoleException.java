package com.challenge.forohub.forohub.infra.errors;

public class ViolationRoleException extends RuntimeException {

    private DetailsErrorDTO error;

    public ViolationRoleException(String message){
        this.error = new DetailsErrorDTO(message);
    }
    public DetailsErrorDTO getError() {
        return error;
    }  
}
