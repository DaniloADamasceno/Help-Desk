package com.backend.configuration;

import com.backend.security.JWTAuthenticationFilter;
import com.backend.security.JWTAuthorizationFilter;
import com.backend.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)                                                     // --> Permite que o back-end seja acessado por perfis específicos
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String[] PUBLIC_MATCHERS = {"/h2-console/**"};                              // --> Permite o acesso ao Banco H2

    //! --------------------------------------------   Dependency Injection  -------------------------------------------
    @Autowired
    private Environment environment;                                                                 //  --> Para imprimir no console

    @Autowired
    private  JWTUtil utilJWT;

    @Autowired
    private UserDetailsService userDetailsService;
    //! --------------------------------------------   FUNCTIONS  ------------------------------------------------------

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        if (Arrays.asList(environment.getActiveProfiles()).contains("test")) {                       // --> Validar se o profile é test
            http.headers().frameOptions().disable();                                                 // --> Permite o acesso ao Banco H2
        }

        http.cors().and().csrf().disable();                                                          // --> Método que permite que o front-end acesse o back-end
        http.addFilter(new JWTAuthenticationFilter(authenticationManager(), utilJWT)); // --> Método que permite que o front-end acesse o back-end
        http.addFilter(new JWTAuthorizationFilter(authenticationManager(), utilJWT, userDetailsService)); // --> Método que permite que o front-end acesse o back-end

        http.authorizeRequests().antMatchers(PUBLIC_MATCHERS).permitAll().
                anyRequest().authenticated();                                                        // --> Permite o acesso ao Banco H2
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    //! --------------------------------------------   METHODS  e BEANS ------------------------------------------------
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();       // --> Método que permite que o front-end acesse o back-end
        configuration.setAllowedMethods(Arrays.asList("DELETE", "POST","GET","PUT"));               // --> Libera os Acessos
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);                               // --> Libera os Acessos
        return source;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {                                          // --> Para criptografar a senha
        return new BCryptPasswordEncoder();
    }
}
