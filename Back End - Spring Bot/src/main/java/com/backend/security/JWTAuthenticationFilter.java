package com.backend.security;

import com.backend.entity.dto.CredentialsDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager managerAuthentication;
    private JWTUtil utilJWT;

    //! ------------------------------------------------   CONSTRUCTOR   -----------------------------------------------
    public JWTAuthenticationFilter(AuthenticationManager managerAuthentication, JWTUtil utilJWT) {
        this.managerAuthentication = managerAuthentication;
        this.utilJWT = utilJWT;
    }

    //! ------------------------------------------------   METHODS   ---------------------------------------------------
    // *--> Método que TENTA autenticar o usuário
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            // *--> Pegando as credenciais do Usuário
            CredentialsDTO credDTO = new ObjectMapper().readValue(request.getInputStream(), CredentialsDTO.class);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    credDTO.getEmail(), credDTO.getPassword(), new ArrayList<>());

            // *--> Tentando autenticar o usuário
            Authentication Authenticated;
            Authenticated = managerAuthentication.authenticate(authenticationToken);
            return Authenticated;
        } catch (Exception exceptionAuthentication) {
            throw new RuntimeException(exceptionAuthentication);
        }
    }

    // *--> Método que se executa quando a autenticação for BEM SUCEDIDA
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {

        String username = ((UserSpringSecurity) authResult.getPrincipal()).getUsername();
        String token = utilJWT.generateToken(username);

        // *--> Adicionando o token na resposta da requisição
        response.addHeader("access-control-expose-headers", "Authorization");
        response.addHeader("Authorization", "Bearer " + token);
    }

    // *--> Método que se executa quando a autenticação FALHAR
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws ServletException, IOException {

        response.setStatus(401);
        response.setContentType("application/json");
        response.getWriter().append(json());

        super.unsuccessfulAuthentication(request, response, failed);
    }

    private CharSequence json() {
        long date = new Date().getTime();
        return "{\"timestamp\": " + date + ", "
                + "\"status\": 401, "
                + "\"error\": \"Not authorized | Não autorizado\", "
                + "\"message\": \"Invalid email or password | Email ou senha inválidos\", "
                + "\"path\": \"/login\"}";
    }
}
