package com.gereciador.estabelecimento.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.gereciador.estabelecimento.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class TokenUtil {

    @Value("${api.security.token.secret}")
    private String secret;
    private final String issuer = "auth-api-gerenciador-estabelecimento";

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret);
            return JWT.create()
                    .withIssuer(this.issuer)
                    .withSubject(user.getUsername())
                    .withExpiresAt(withExpiresAt())
                    .sign(algorithm);

        } catch (JWTCreationException e) {
            throw new RuntimeException(e);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret);
            return JWT.require(algorithm)
                    .withIssuer(this.issuer)
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTCreationException e) {
            throw new RuntimeException(e);
        }
    }


    private Instant withExpiresAt() {
        return LocalDateTime.now().plusHours(3).toInstant(ZoneOffset.of("-03:00"));
    }
}
