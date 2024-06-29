package com.challenge.forohub.forohub.domain.models.user.service;


import org.springframework.http.ResponseEntity;

import com.challenge.forohub.forohub.domain.models.user.dto.UserValidationDTO;
import com.challenge.forohub.forohub.domain.models.user.persistence.User;

public interface IUserService {
    public ResponseEntity<?> register(UserValidationDTO validationDTO);
    public ResponseEntity<?> findUser(Long id);
    public User findUserToCreateTopic(Long id);
}
