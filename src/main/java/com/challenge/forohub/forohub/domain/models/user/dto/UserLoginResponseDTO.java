package com.challenge.forohub.forohub.domain.models.user.dto;

public record UserLoginResponseDTO(
        String user,
        String status,
        String jwt_token) {

}
