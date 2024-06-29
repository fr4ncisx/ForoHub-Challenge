package com.challenge.forohub.forohub.domain.models.user.dto;

import com.challenge.forohub.forohub.domain.models.user.persistence.User;

public record UserDetailsDTO(
    Long id,
    String username,
    String email,
    String password
) {
    public UserDetailsDTO(User user) {
        this(user.getId(), user.getUsername(), user.getEmail(), encoder(user.getPassword()));
    }

    private static String encoder(String password){
        return password;
    }
}
