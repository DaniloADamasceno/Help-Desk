package com.backend.security;

import com.backend.entity.enums.Profile;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class UserSpringSecurity  implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Getter
    private Integer id;

    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    //! -------------------------------------------   Constructors   ---------------------------------------------------
    public UserSpringSecurity(Integer id, String email, String password, Set<Profile> profile) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = profile.stream().map(x -> new SimpleGrantedAuthority
                (x.getDescription())).collect(Collectors.toSet());
    }

    //! -------------------------------------------   Methods   --------------------------------------------------------
    // Lista de autorizações
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    // SENHA
    @Override
    public String getPassword() {
        return password;
    }

    // NOME DE USUARIO
    @Override
    public String getUsername() {
        return email;
    }

    // CONTA EXPIRADA
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // CONTA BLOQUEADA
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // CREDENCIAIS EXPIRADAS
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // CONTA HABILITADA
    @Override
    public boolean isEnabled() {
        return true;
    }
}
