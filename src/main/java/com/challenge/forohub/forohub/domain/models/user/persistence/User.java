package com.challenge.forohub.forohub.domain.models.user.persistence;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.challenge.forohub.forohub.domain.models.user.dto.UserDataDTO;
import com.challenge.forohub.forohub.domain.models.user.dto.UserValidationDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String username;
  private String email;
  private String password;

  public User(UserValidationDTO validationDTO, String encodedPassword) {
    this.username = validationDTO.username();
    this.email = validationDTO.email();
    this.password = encodedPassword;
  }

  public User(UserDataDTO user) {
    this.username = user.username();
    this.email = user.email();
  }

  public User(Long user_id) {
    this.id = user_id;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority("ROLE_USER"));
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

}
