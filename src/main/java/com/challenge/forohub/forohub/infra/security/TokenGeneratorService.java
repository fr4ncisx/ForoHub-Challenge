package com.challenge.forohub.forohub.infra.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.auth0.jwt.algorithms.Algorithm;

@Service
public class TokenGeneratorService {

  @Value("{SECRET.KEY}")
  private String secretKey;

  @Value("{TIME.EXPIRATION}")
  private String timeExpiration;

  /*public String generateToken(Authentication authentication) {
    Algorithm algorithm = Algorithm.HMAC256(secretKey);
    String username = authentication.getPrincipal().toString();
    return null;

  }*/

}
