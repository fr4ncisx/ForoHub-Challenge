package com.challenge.forohub.forohub.domain.models.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserValidationDTO(
  @NotBlank String username,
  @Email(message = "El email ingresado no cumple con el formato de email") @NotBlank(message = "Email no debe estar vacío") @NotBlank String email,
  @NotBlank @Size(min = 8, message = "La contraseña debe tener mínimo 8 caracteres") String password
) {
}
