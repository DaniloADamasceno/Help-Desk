package com.backend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtil {

    // *--> Pega o valor da variável de ambiente do Application.properties
    @Value("${jwt.expiration}")
    private Long expiration;

    // *--> Pega o valor da variável de ambiente do Application.properties
    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)                                                           // *--> Informações que eu quero colocar dentro do token
                .setExpiration(new Date(System.currentTimeMillis() + expiration))      // *--> Data de expiração do token
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())     // *--> Assinatura do token
                .compact();                                                                  // *--> Compacta o token
    }

    public boolean tokenValido(String token) {

        Claims claims = getClaims(token);
        if (claims != null) {
            String username = claims.getSubject();
            Date expirationDate = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());

            // *--> Verifica se o token é válido
            return username != null && expirationDate != null && now.before(expirationDate);
        }
        return false;
    }

    private Claims getClaims(String token) {

        try {
            // *--> Recupera os Claims a partir de um token
            return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
        } catch (Exception exceptionClaims) {
            return null;
        }
    }

    public String getUsername(String token) {

        Claims claims = getClaims(token);
        if (claims != null) {
            return claims.getSubject();                                                             // *--> Retorna o usuário
        }
        return null;
    }
}
