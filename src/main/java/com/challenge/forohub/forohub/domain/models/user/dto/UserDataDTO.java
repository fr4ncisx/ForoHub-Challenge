package com.challenge.forohub.forohub.domain.models.user.dto;

import com.challenge.forohub.forohub.domain.models.user.persistence.User;

public record UserDataDTO(
    String username,
    String email
) {
    public UserDataDTO(User userFound) {
        this(userFound.getUsername(), userFound.getEmail());
    }
}
