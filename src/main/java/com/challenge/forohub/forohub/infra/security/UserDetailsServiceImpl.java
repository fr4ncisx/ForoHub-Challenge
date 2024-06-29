package com.challenge.forohub.forohub.infra.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.challenge.forohub.forohub.domain.models.user.persistence.User;
import com.challenge.forohub.forohub.domain.models.user.persistence.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    UserRepository repository;    

    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userFound = repository.findByUsername(username);
        if(userFound.isPresent()){
           return userFound.get();
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        
    }

}
