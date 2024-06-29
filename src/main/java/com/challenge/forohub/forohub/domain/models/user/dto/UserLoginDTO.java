package com.challenge.forohub.forohub.domain.models.user.dto;

import jakarta.validation.constraints.NotBlank;

public record UserLoginDTO(
@NotBlank
String username,
@NotBlank
String password) {

}
