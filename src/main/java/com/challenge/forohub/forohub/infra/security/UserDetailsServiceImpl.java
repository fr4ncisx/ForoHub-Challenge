package com.challenge.forohub.forohub.infra.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.challenge.forohub.forohub.domain.models.user.dto.UserLoginDTO;
import com.challenge.forohub.forohub.domain.models.user.dto.UserLoginResponseDTO;
import com.challenge.forohub.forohub.domain.models.user.persistence.User;
import com.challenge.forohub.forohub.domain.models.user.persistence.UserRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository repository;
    private JwtUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDetailsServiceImpl(UserRepository repository, JwtUtils jwtUtils) {
        this.repository = repository;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userFound = repository.findByUsername(username);
        if (userFound.isPresent()) {
            return userFound.get();
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

    }

    public ResponseEntity<?> loginUser(UserLoginDTO userLogin) {
        String user = userLogin.username();
        String password = userLogin.password();

        Authentication authentication = authenticate(user, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generateToken(authentication);
        UserLoginResponseDTO response = new UserLoginResponseDTO(user, "Inicio de sesión exitoso", token);
        return ResponseEntity.ok(response);
    }

    private Authentication authenticate(String user, String password) {
        UserDetails userDetails = loadUserByUsername(user);
        if (userDetails == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        if(!passwordEncoder.matches(password, userDetails.getPassword())){
            throw new BadCredentialsException("Usuario no encontrado");
        }
        return new UsernamePasswordAuthenticationToken(user, userDetails.getPassword());
    }

}
