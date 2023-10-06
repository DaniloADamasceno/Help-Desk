package com.backend.security;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

@Component
public class JWTUtil {

    @Value("${jwt.expiration}")                                                              // --> Pega o valor da variável de ambiente do Application.properties
    private Long expiration;

    @Value("${jwt.secret}")                                                                  // --> Pega o valor da variável de ambiente do Application.properties
    private String secret;

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)                                                           // --> Informações que eu quero colocar dentro do token
                .setExpiration(new Date(System.currentTimeMillis() + expiration))      // --> Data de expiração do token
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())     // --> Assinatura do token
                .compact();                                                                  // --> Compacta o token
    }
}
