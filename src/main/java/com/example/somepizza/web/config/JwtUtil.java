package com.example.somepizza.web.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {
    private static final String SECRET = "somepizzasecretkey";
    private static Algorithm ALGORITHM = Algorithm.HMAC256(SECRET);

    public String create(String username) {
        return JWT.create()
                .withSubject(username)
                .withIssuer("somepizza")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(1)))
                .sign(ALGORITHM);
    }
}
