package com.challenge.forohub.forohub.domain.models.user.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.challenge.forohub.forohub.domain.models.user.dto.UserDataDTO;
import com.challenge.forohub.forohub.domain.models.user.dto.UserDetailsDTO;
import com.challenge.forohub.forohub.domain.models.user.dto.UserValidationDTO;
import com.challenge.forohub.forohub.domain.models.user.persistence.User;
import com.challenge.forohub.forohub.domain.models.user.persistence.UserRepository;
import com.challenge.forohub.forohub.infra.errors.DuplicatedEntryError;
import com.challenge.forohub.forohub.infra.errors.UserNotFoundException;
import com.challenge.forohub.forohub.infra.errors.ViolationRoleException;

@Service
public class UserServiceImpl implements IUserService {

    private UserRepository repository;
    private PasswordEncoder encoder;

    public UserServiceImpl(UserRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    @Override
    public ResponseEntity<?> register(UserValidationDTO validationDTO) {
        var preventCreateAdminUser = validationDTO.username().toLowerCase().contains("admin");
        if (preventCreateAdminUser) {
            throw new ViolationRoleException(
                    "No se pudo crear el usuario '" + validationDTO.username() + "' no está permitido");
        }
        // Find user if already exists to prevent creating new ids
        var username = repository.findByUsername(validationDTO.username());
        if (!username.isPresent()) {
            String encodedPassword = encoder.encode(validationDTO.password());
            User user = repository.save(new User(validationDTO, encodedPassword));
            return new ResponseEntity<>(new UserDetailsDTO(user), HttpStatus.CREATED);
        } else {
            throw new DuplicatedEntryError("El usuario " + validationDTO.username() + " ya existe");
        }
    }

    @Override
    public ResponseEntity<?> findUser(Long id) {
        var userFound = repository.findById(id);
        if (userFound.isPresent()) {
            return ResponseEntity.ok(new UserDataDTO(userFound.get()));
        } else {
            throw new UserNotFoundException("Usuario no encontrado");
        }
    }

    @Override
    public User findUserToCreateTopic(Long id) {
        var userFound = repository.findById(id);
        if (userFound.isPresent()) {
            return userFound.get();
        } else {
            throw new UserNotFoundException("El id " + id + " no existe en la base de datos");
        }
    }
}
