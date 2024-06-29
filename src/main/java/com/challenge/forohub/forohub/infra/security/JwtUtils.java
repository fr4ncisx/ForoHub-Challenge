package com.challenge.forohub.forohub.infra.security;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class JwtUtils {

  @Value("{SECRET.KEY}")
  private String secretKey;

  public String generateToken(Authentication auth) {
    Algorithm algo = Algorithm.HMAC256(secretKey);
    return JWT.create()
        .withIssuer("Foro hub tokenizer")
        .withSubject(auth.getPrincipal().toString())
        .withExpiresAt(LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.of("-03:00")))
        .withJWTId(UUID.randomUUID().toString())
        .sign(algo);
  }

  public DecodedJWT validateToken(String token) {
    try {
      Algorithm algo = Algorithm.HMAC256(secretKey);
      JWTVerifier verifier = JWT.require(algo)
      .withIssuer("Foro hub tokenizer")
      .build();
      DecodedJWT decodedJWT = verifier.verify(token);
      return decodedJWT;      
    } catch (JWTVerificationException e) {
      throw new JWTVerificationException("Token inválido");
    }
  }
  public String extractUsername(DecodedJWT decodedJWT){
    return decodedJWT.getSubject().toString();
  }
}
