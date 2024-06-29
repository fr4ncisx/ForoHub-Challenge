package com.challenge.forohub.forohub.domain.models.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.forohub.forohub.domain.models.user.dto.UserLoginDTO;
import com.challenge.forohub.forohub.domain.models.user.dto.UserValidationDTO;
import com.challenge.forohub.forohub.domain.models.user.service.IUserService;
import com.challenge.forohub.forohub.infra.security.UserDetailsServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    private IUserService service;
    private UserDetailsServiceImpl authService;   

    public UserController(IUserService service, UserDetailsServiceImpl authService) {
        this.service = service;
        this.authService = authService;
    }

    @PostMapping("/new")
    @Transactional(readOnly = false)
    @Operation(summary = "Crear nuevo usuario", description = "Se crea un nuevo usuario en la base de datos, se debe ingresar una contraseña mayor a 8 caracteres")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserValidationDTO validationDTO){
        return service.register(validationDTO);
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    @Operation(summary = "Se busca un usuario por id", description = "Se ingresa el id después del /user/")
    public ResponseEntity<?> findUserById(@PathVariable Long id){
        return service.findUser(id);
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody UserLoginDTO userLogin){
        return authService.loginUser(userLogin);
    }
}



